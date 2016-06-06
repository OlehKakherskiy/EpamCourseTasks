package controller.validator;

import controller.ValidationException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class IncompatibleStringValueValidatorTest {

    static Map<String, Class> templates;

    static AbstractValidator<Map<String, Class>, Map<String, Object>> validator;

    @BeforeClass
    public static void init() {
        templates = new HashMap<>();
        templates.put("name", String.class);
        templates.put("bankName", String.class);
        templates.put("maxMonthCount", Integer.class);
        templates.put("minSumAmount", Integer.class);
        templates.put("maxSumAmount", Integer.class);
        templates.put("earlyRepaymentPossibility", Boolean.class);
        templates.put("increaseMaxCreditLimitOpportunity", Boolean.class);

        validator = new IncompatibleStringValueValidator(null);
    }

    @Test
    public void testValidateHookAllOk() throws Exception {
        Map<String, Object> actualParams = new HashMap<>();
        actualParams.put("name", "name1");
        actualParams.put("bankName", "bankName1");
        actualParams.put("minSumAmount", 1);
        validator.validate(templates, actualParams);
    }

    @Test(expected = ValidationException.class)
    public void testValidateHookIncompatibleTypes() throws Exception {
        Map<String, Object> actualParams = new HashMap<>();
        actualParams.put("name", "name1");
        actualParams.put("bankName", "bankName1");
        actualParams.put("minSumAmount", 1000.5);
        validator.validate(templates, actualParams);
    }

    @Test(expected = ValidationException.class)
    public void testValidateHookNoTemplateMatches() throws Exception {
        Map<String, Object> actualParams = new HashMap<>();
        actualParams.put("blablabla", 10);
        actualParams.put("nextFakeParam", 20);
        actualParams.put("lastFakeParam", "todo");
        validator.validate(templates, actualParams);
    }

    @Test
    public void testValidateHookExtraParams() throws Exception {
        Map<String, Object> actualParams = new HashMap<>();
        actualParams.put("name", "name1");
        actualParams.put("bankName", "bankName1");
        actualParams.put("minSumAmount", 1);
        actualParams.put("blablabla", 10);
        actualParams.put("nextFakeParam", 20);
        actualParams.put("lastFakeParam", "todo");

        validator.validate(templates, actualParams);
    }

    @Test(expected = ValidationException.class)
    public void testEmptyStringsValidation() throws Exception {
        Map<String, Object> actualParams = new HashMap<>();
        actualParams.put("name", "");
        actualParams.put("bankName", "asdfasd");
        actualParams.put("minSumAmount", 1);

        validator.validate(templates, actualParams);
    }

    @Test(expected = ValidationException.class)
    public void testNullStringValuesValidation() throws Exception {
        Map<String, Object> actualParams = new HashMap<>();
        actualParams.put("name", "aewtwt");
        actualParams.put("bankName", null);
        actualParams.put("minSumAmount", "1");

        validator.validate(templates, actualParams);
    }
}
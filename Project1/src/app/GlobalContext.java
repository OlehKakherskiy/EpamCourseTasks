package app;

import model.CreditList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class GlobalContext {

    public static final Properties contextParams = new Properties();

    public static final String incompatibleTypesExceptionKey = "exception.incompatibleTypesException";

    public static final String nullStringValidationException = "exception.stringIsNullValidationException";

    public static final String emptyStringValidationException = "exception.stringIsEmptyValidationException";

    public static final String noCreditException = "exception.chooseCreditException";

    public static final String trueFalseParseException = "exception.trueFalseParseException";

    public static final String availableCommands = "availableCommands";

    public static final String availableKeysFilters = "filterKeysInput";

    public static final String chooseCredit = "chooseCredit";

    public static final String noCommand = "exception.noCommandException";

    public static final CreditList creditList = new CreditList();

    static {
        initConfigs();
    }

    public static Object getParam(String key) {
        return contextParams.get(key);
    }


    public static void initConfigs() {
        try (FileInputStream fis = new FileInputStream("Project1/src/app/config.properties")) {
            contextParams.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

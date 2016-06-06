package controller.command;

import app.GlobalContext;
import model.CreditList;
import model.entity.credit.Credit;
import model.entity.credit.CreditLine;
import model.entity.credit.PaymentStrategy;
import model.entity.credit.TargetCredit;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class FindCreditCommandTest {

    public static CreditList creditList = GlobalContext.creditList;

    public static FindCreditCommand testCommand = new FindCreditCommand(null, null);

    @BeforeClass
    public static void init() {
        Credit credit1 = new TargetCredit("credit1", "bank1", 60, 10000, 100000, true, "simpleTarget", null, PaymentStrategy.ANNUITY);
        Credit credit2 = new CreditLine("creditLine1", "bank1", 1, 2000, 10000, false, null, PaymentStrategy.FULLY);
        Credit credit6 = new TargetCredit("credit6", "bank1", 2, 1300000, 200000000, true, "justTarget2", null, PaymentStrategy.DIFFERENTIATED);
        Credit credit3 = new TargetCredit("credit2", "bank2", 50, 15000, 150000, false, "house", null, PaymentStrategy.DIFFERENTIATED);
        Credit credit4 = new CreditLine("creditLine2", "bank2", 1, 5000, 15000, true, null, PaymentStrategy.FULLY);
        Credit credit5 = new TargetCredit("credit5", "bank1", 100, 1500000, 300000000, true, "justTarget", null, PaymentStrategy.DIFFERENTIATED);
        creditList.addCredits(credit1, credit2, credit6, credit3, credit4, credit5);
        Map<String, Class> paramsTemplate = new HashMap<>();

        paramsTemplate.put("name", String.class);
        paramsTemplate.put("bankName", String.class);
        paramsTemplate.put("earlyRepaymentPossibility", Boolean.class);
        paramsTemplate.put("increaseMaxCreditLimitOpportunity", Boolean.class);
        paramsTemplate.put("paymentStrategy", PaymentStrategy.class);

        testCommand.setParamsTemplate(paramsTemplate);
    }

    @Test
    public void testProcessCommandHook_bankName() throws Exception {
        Set<Credit> expectation = new HashSet<>();
        expectation.add(new TargetCredit("credit1", "bank1", 60, 10000, 100000, true, "simpleTarget", null, PaymentStrategy.ANNUITY));
        expectation.add(new CreditLine("creditLine1", "bank1", 1, 2000, 10000, false, null, PaymentStrategy.FULLY));
        expectation.add(new TargetCredit("credit5", "bank1", 100, 1500000, 300000000, true, "justTarget", null, PaymentStrategy.DIFFERENTIATED));
        expectation.add(new TargetCredit("credit6", "bank1", 2, 1300000, 200000000, true, "justTarget2", null, PaymentStrategy.DIFFERENTIATED));

        Map<String, Object> params = new HashMap<>();
        params.put("bankName", "bank1");
        Assert.assertArrayEquals(expectation.toArray(), testCommand.processCommandHook(params).toArray());
    }


    @Test
    public void testProcessCommandHook_paymentStrategy() throws Exception {
        Object[] expectation = {
                new TargetCredit("credit2", "bank2", 50, 15000, 150000, false, "house", null, PaymentStrategy.DIFFERENTIATED),
                new TargetCredit("credit5", "bank1", 100, 1500000, 300000000, true, "justTarget", null, PaymentStrategy.DIFFERENTIATED),
                new TargetCredit("credit6", "bank1", 2, 1300000, 200000000, true, "justTarget2", null, PaymentStrategy.DIFFERENTIATED)
        };

        Map<String, Object> params = new HashMap<>();
        params.put("paymentStrategy", PaymentStrategy.DIFFERENTIATED);

        Assert.assertArrayEquals(expectation, testCommand.processCommandHook(params).toArray());
    }

    @Test
    public void testProcessCommandHook_emptyResult() throws Exception {

        Map<String, Object> params = new HashMap<>();

        Assert.assertArrayEquals(new Object[0], testCommand.processCommandHook(params).toArray());

        params.put("name", "blabla");

        Assert.assertArrayEquals(new Object[0], testCommand.processCommandHook(params).toArray());
    }

    @Test
    public void testProcessCommandHook_complexRequest() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("bankName", "bank1");
        params.put("paymentStrategy", PaymentStrategy.DIFFERENTIATED);
        params.put("earlyRepaymentPossibility", false);
        Object[] expectation = {
                new TargetCredit("credit6", "bank1", 2, 1300000, 200000000, true, "justTarget2", null, PaymentStrategy.DIFFERENTIATED),
                new TargetCredit("credit5", "bank1", 100, 1500000, 300000000, true, "justTarget", null, PaymentStrategy.DIFFERENTIATED)
        };
        Assert.assertArrayEquals(new Object[0], testCommand.processCommandHook(params).toArray());
    }
}
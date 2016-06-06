package controller.command;

import app.GlobalContext;
import model.entity.credit.Credit;
import model.entity.credit.CreditLine;
import model.entity.credit.PaymentStrategy;
import model.entity.credit.TargetCredit;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ChooseCreditByNameCommandTest {

    @Test
    public void testProcessCommandHookShouldBeFound() throws Exception {
        ChooseCreditByNameCommand testCommand = new ChooseCreditByNameCommand(null, null, null, null);
        Credit credit1 = new TargetCredit("credit1", "bank1", 60, 10000, 100000, true, "simpleTarget", null, PaymentStrategy.ANNUITY);
        Credit credit2 = new CreditLine("creditLine1", "bank1", 1, 2000, 10000, false, null, PaymentStrategy.FULLY);
        Credit credit3 = new TargetCredit("credit2", "bank2", 50, 15000, 150000, false, "house", null, PaymentStrategy.DIFFERENTIATED);
        Credit credit4 = new CreditLine("creditLine2", "bank2", 1, 5000, 15000, true, null, PaymentStrategy.FULLY);
        Credit credit5 = new TargetCredit("credit5", "bank1", 100, 1500000, 300000000, true, "justTarget", null, PaymentStrategy.ANNUITY);

        GlobalContext.creditList.addCredits(credit1, credit2, credit3, credit4);

        Map<String, Object> params = new HashMap<>();
        params.put("creditName", "credit1");
        Assert.assertEquals(credit1, testCommand.processCommandHook(params));
        params.put("creditName", "blablaCredit");
        Assert.assertNull(testCommand.processCommandHook(params));
        Assert.assertNull(testCommand.processCommandHook(null));
    }
}
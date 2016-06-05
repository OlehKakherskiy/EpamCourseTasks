package app;

import controller.CreditOperationsController;
import controller.command.AbstractCommand;
import controller.command.ChooseCreditByNameCommand;
import controller.command.FindCreditCommand;
import model.entity.credit.Credit;
import model.entity.credit.CreditLine;
import model.entity.credit.PaymentStrategy;
import model.entity.credit.TargetCredit;
import view.View;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) throws IntrospectionException {
        Credit credit1 = new TargetCredit("credit1", "bank1", 60, 10000, 100000, true, "simpleTarget", null, PaymentStrategy.ANNUITY);
        Credit credit2 = new CreditLine("creditLine1", "bank1", 1, 2000, 10000, false, null, PaymentStrategy.FULLY);
        Credit credit3 = new TargetCredit("credit2", "bank2", 50, 15000, 150000, false, "house", null, PaymentStrategy.DIFFERENTIATED);
        Credit credit4 = new CreditLine("creditLine2", "bank2", 1, 5000, 15000, true, null, PaymentStrategy.FULLY);
        GlobalContext.creditList.addCredits(credit1, credit2, credit3, credit4);

        Map<Integer, AbstractCommand> commandMap = new HashMap<>();
        View view = new View();
        Scanner sc = new Scanner(System.in);
        commandMap.put(1, new AbstractCommand<Set<Credit>, Void, Void>(view, sc) {

            @Override
            protected Void inputParametersForProcessing() {
                return null;
            }

            @Override
            protected Set<Credit> processCommandHook(Void params) {
                return GlobalContext.creditList.getCredits();
            }

            @Override
            protected void outputResult(Set<Credit> result) {
                StringBuilder builder = new StringBuilder("available credits:\n");
                for (Credit credit : result) {
                    builder.append(credit.getOfferDetails()).append("\n\n");
                }
                view.printMessage(builder.toString());
            }
        });
        commandMap.put(2, new FindCreditCommand(view, sc));
        commandMap.put(3, new ChooseCreditByNameCommand(view, sc));

        CreditOperationsController controller = new CreditOperationsController(view, sc, commandMap);

        controller.processRequest();
    }

}

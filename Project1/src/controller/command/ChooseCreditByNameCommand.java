package controller.command;

import model.CreditList;
import model.entity.credit.Credit;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ChooseCreditByNameCommand implements IOperationCommand<Credit, String> {

    private CreditList creditList;

    public ChooseCreditByNameCommand(CreditList creditList) {
        this.creditList = creditList;
    }

    @Override
    public Credit processCommand(String params) {
        return creditList.getCredits().stream().filter(credit -> credit.getName().
                equals(params)).findFirst().get();
    }
}
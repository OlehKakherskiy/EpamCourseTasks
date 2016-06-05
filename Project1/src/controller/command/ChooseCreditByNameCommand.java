package controller.command;

import app.GlobalContext;
import controller.validator.AbstractValidator;
import model.CreditList;
import model.entity.credit.Credit;
import view.View;

import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ChooseCreditByNameCommand extends AbstractCommand<Credit, Class<String>, String> {

    private CreditList creditList = GlobalContext.creditList;

    private String inputtedString = null;

    public ChooseCreditByNameCommand(View view, Scanner sc) {
        super(view, sc);
    }

    public ChooseCreditByNameCommand(View view, Scanner sc,
                                     AbstractValidator<Class<String>, String> validator, Class<String> paramsTemplate) {
        super(view, sc, validator, paramsTemplate);
    }


    @Override
    protected String inputParametersForProcessing() {
        view.printMessage((String) GlobalContext.getParam(GlobalContext.chooseCredit));
        inputtedString = scanner.nextLine();
        return inputtedString;
    }

    @Override
    protected Credit processCommandHook(String params) {
        return creditList.getCredits().stream().filter(credit -> credit.getName().
                equals(params)).findFirst().get();
    }

    @Override
    protected void outputResult(Credit result) {
        if (result == null) {
            view.printMessage(String.format((String) GlobalContext.getParam(GlobalContext.noCreditException), inputtedString));
        }
        view.printMessage("result credit: " + result.toString());
    }
}
package controller.command;

import app.GlobalContext;
import controller.validator.AbstractValidator;
import model.CreditList;
import model.entity.credit.Credit;
import view.View;

import java.util.Scanner;

/**
 * Class encapsulates logic of choosing credit from list by it's name.
 * No validation is needed
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ChooseCreditByNameCommand extends AbstractCommand<Credit, Class<String>, String> {

    private CreditList creditList = GlobalContext.creditList;

    /**
     * credit's name inputted string
     */
    private String inputtedString = null;

    public ChooseCreditByNameCommand(View view, Scanner sc) {
        super(view, sc);
    }

    public ChooseCreditByNameCommand(View view, Scanner sc,
                                     AbstractValidator<Class<String>, String> validator, Class<String> paramsTemplate) {
        super(view, sc, validator, paramsTemplate);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected String inputParametersForProcessing() {
        view.printMessage((String) GlobalContext.getParam(GlobalContext.chooseCredit));
        while (scanner.hasNextLine() && (inputtedString == null || inputtedString.equals("")))
            inputtedString = scanner.nextLine();
        return inputtedString;
    }

    /**
     * {@inheritDoc}
     * <p>
     *
     * @param params credit's name.
     * @return credit that has inputted name
     */
    @Override
    protected Credit processCommandHook(String params) {
        for (Credit credit : creditList.getCredits()) {
            if (credit.getName().equals(params))
                return credit;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void outputResult(Credit result) {
        if (result == null) {
            view.printMessage(String.format((String) GlobalContext.getParam(GlobalContext.noCreditException), inputtedString));
        }
        view.printMessage("result credit: " + result.toString());
    }
}
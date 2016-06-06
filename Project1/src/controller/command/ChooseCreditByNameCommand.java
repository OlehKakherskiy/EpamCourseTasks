package controller.command;

import app.GlobalContext;
import controller.validator.AbstractValidator;
import model.CreditList;
import model.entity.credit.Credit;
import view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class encapsulates logic of choosing credit from list by it's name.
 * No validation is needed
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ChooseCreditByNameCommand extends AbstractCommand<Credit> {

    private CreditList creditList = GlobalContext.creditList;

    /**
     * credit's name inputted string
     */
    private String inputtedString = null;


    public ChooseCreditByNameCommand(View view, Scanner sc,
                                     AbstractValidator validator, Map<String, Class> paramsTemplate) {
        super(view, sc, validator, paramsTemplate);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Object> inputParametersForProcessing() {
        view.printMessage((String) GlobalContext.getParam(GlobalContext.chooseCredit));
        while (scanner.hasNextLine() && (inputtedString == null || inputtedString.equals("")))
            inputtedString = scanner.nextLine();
        Map<String, Object> result = new HashMap<>();
        result.put("creditName", inputtedString);
        return result;
    }

    /**
     * {@inheritDoc}
     * <p>
     *
     * @param params credit's name.
     * @return credit that has inputted name
     */
    @Override
    protected Credit processCommandHook(Map<String, Object> params) {
        if (params == null)
            return null;
        String creditName = (String) params.get("creditName");
        for (Credit credit : creditList.getCredits()) {
            if (credit.getName().equals(creditName))
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
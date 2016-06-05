package controller;

import controller.command.IOperationCommand;
import model.CreditList;
import view.View;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CreditOperationsController {

    private CreditList creditList;

    private View view;

    private Map<Integer, IOperationCommand> controllerCommands;

    public CreditOperationsController(CreditList creditList, Map<Integer, IOperationCommand> commands) {
        this.creditList = creditList;
        this.controllerCommands = commands;
    }

    public void processRequest() {
        Scanner sc = new Scanner(System.in);
        int commandID = inputCommandID(sc);
    }

    private int inputCommandID(Scanner sc) {
        boolean commandIDExists = false;
        int commandID;
        do {
            commandID = sc.nextInt();
            commandIDExists = validateCommandID(commandID);
            if (!commandIDExists) {
                System.out.println("This command id does not exists. Try again.");
            }
        } while (commandIDExists);

        return commandID;
    }

    private boolean validateCommandID(int commandID) {
        return controllerCommands.containsKey(commandID);
    }
}
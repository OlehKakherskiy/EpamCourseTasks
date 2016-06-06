package controller;

import app.GlobalContext;
import controller.command.AbstractCommand;
import view.View;

import java.util.Map;
import java.util.Scanner;

/**
 * Controller in MVC architecture. Has list of commands and
 * user can choose each of them.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CreditOperationsController {

    /**
     * view
     */
    private View view;

    /**
     * interaction with user
     */
    private Scanner sc;

    /**
     * controller commands
     */
    private Map<Integer, AbstractCommand> controllerCommands;

    public CreditOperationsController(View view, Scanner sc, Map<Integer, AbstractCommand> commands) {
        this.controllerCommands = commands;
        this.view = view;
        this.sc = sc;
    }

    /**
     * processes command from the list. If no command - user will be shown exception message
     */
    public void processRequest() {
        while (true) {
            view.printMessage((String) GlobalContext.getParam(GlobalContext.availableCommands));
            int commandID = -1;
            if (sc.hasNextInt())
                commandID = sc.nextInt();
            if (commandID == 0) {
                System.exit(0);
            } else {
                AbstractCommand command = controllerCommands.get(commandID);
                if (command == null) {
                    view.printMessage((String) GlobalContext.getParam(GlobalContext.noCommand));
                } else {
                    command.processCommand();
                }
            }
        }
    }
}
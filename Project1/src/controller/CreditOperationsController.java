package controller;

import app.GlobalContext;
import controller.command.AbstractCommand;
import view.View;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CreditOperationsController {

    private View view;

    private Scanner sc;

    private Map<Integer, AbstractCommand> controllerCommands;

    public CreditOperationsController(View view, Scanner sc, Map<Integer, AbstractCommand> commands) {
        this.controllerCommands = commands;
        this.view = view;
        this.sc = sc;
    }

    public void processRequest() {
        while (true) {
            view.printMessage((String) GlobalContext.getParam(GlobalContext.availableCommands));
            int commandID = sc.nextInt();
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
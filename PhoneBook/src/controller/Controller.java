package controller;

import controller.parser.AbstractDataParser;
import model.Contact;
import model.NoteBook;
import view.View;

import java.util.List;
import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Controller {

    private View view;

    private NoteBook noteBook;

    private List<AbstractDataParser> dataParsers;

    public Controller(View view, NoteBook noteBook, List<AbstractDataParser> dataParsers) {
        this.view = view;
        this.noteBook = noteBook;
        this.dataParsers = dataParsers;
    }

    public void processUser() {
        Scanner scanner = new Scanner(System.in);
        Contact contact = new Contact();
        for (AbstractDataParser parser : dataParsers) {
            parseData(scanner, parser, contact);
        }
        view.printMessage("Contact is created");
        noteBook.addContact(contact);
        view.printMessage(noteBook.toString());
    }

    private void parseData(Scanner sc, AbstractDataParser parser, Contact contact) {
        boolean dataIsValid = false;
        do {
            view.printMessage(parser.getParsingRulesMessage());
            dataIsValid = parser.parseData(contact, sc.nextLine());
            if (!dataIsValid) {
                view.printMessage(parser.getErrorMessage());
            }
        } while (!dataIsValid);
    }
}

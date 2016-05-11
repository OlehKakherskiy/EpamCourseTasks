package controller;

import controller.parser.AbstractDataParser;
import model.Contact;
import model.PhoneBook;
import view.View;

import java.util.List;
import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Controller {

    private View view;

    /**
     * phone book
     */
    private PhoneBook phoneBook;

    /**
     * parsers, that parse inputted data and complete model with valid data
     */
    private List<AbstractDataParser> dataParsers;

    /**
     * constructor that initialize all fields
     *
     * @param view
     * @param PhoneBook
     * @param dataParsers
     */
    public Controller(View view, PhoneBook PhoneBook, List<AbstractDataParser> dataParsers) {
        this.view = view;
        this.phoneBook = PhoneBook;
        this.dataParsers = dataParsers;
    }

    /**
     * Creates new {@link Contact} object, parse all data using {@link #dataParsers}
     * and initialize all fields with valid data. If all data is valid, then model is completed
     * by {@link AbstractDataParser} and added to {@link #phoneBook}.
     */
    public void processUser() {
        Scanner scanner = new Scanner(System.in);
        Contact contact = new Contact();
        for (AbstractDataParser parser : dataParsers) {
            parseData(scanner, parser, contact);
        }
        view.printMessage("Contact is created");
        phoneBook.addContact(contact);
        view.printMessage(phoneBook.toString());
    }

    /**
     * check next input line using parser object and complete object model. If data isn't
     * valid, prints {@link AbstractDataParser#errorMessage}.
     *
     * @param sc input stream from which data is gotten
     * @param parser used for parsing data and completing if data is valid
     * @param contact object that is completed by valid data
     */
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

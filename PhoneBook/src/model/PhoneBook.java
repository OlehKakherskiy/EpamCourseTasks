package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents phone book, that can be empty or have several {@link Contact} objects.
 *
 * @see Contact
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class PhoneBook implements Serializable {

    /**
     * contact list
     */
    private List<Contact> contacts;

    /**
     * inits {@link #contacts} field with empty list.
     */
    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    /**
     * add contact to contact list. If contact has already been added,
     * then changes contact {@link Contact#updateDate}
     *
     * @param contact contact that has to be added to list
     */
    public void addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        } else {
            contact.setUpdateDate(LocalDate.now());
        }
    }

    /**
     * removes contact from contact list. If there is no {@link Contact} object in list,
     * then do nothing.
     *
     * @param contact contact object, that should be removed.
     */
    public void removeContact(Contact contact) {
        if (contacts.contains(contact)) {
            contacts.remove(contact);
        }
    }

    @Override
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder("contact list:\n");
        for (int i = 0; i < contacts.size(); i++) {
            resultBuilder.append(i).append(".").append(contacts.get(i).toString()).append("\n\n");
        }
        return resultBuilder.toString();
    }
}

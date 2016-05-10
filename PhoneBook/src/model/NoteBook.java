package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class NoteBook implements Serializable {

    private List<Contact> contacts;

    public NoteBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        } else {
            contact.setUpdateDate(LocalDate.now());
        }
    }

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

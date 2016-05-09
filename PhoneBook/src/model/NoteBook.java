package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class NoteBook implements Serializable {

    private List<Contact> contacts;

    public void addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        }else{
            contact.setUpdateDate(LocalDate.now());
        }
    }

    public void removeContact(Contact contact) {
        if (contacts.contains(contact)) {
            contacts.remove(contact);
        }
    }
}

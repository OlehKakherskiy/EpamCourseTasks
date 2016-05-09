package model;

import entity.Address;
import entity.ContactGroup;
import entity.ContactName;
import entity.PhoneNumber;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Contact implements Serializable {

    private UUID id;

    private ContactName contactName;

    private ContactGroup contactGroup;

    private String nickName;

    private String contactComment;

    private String skypeNickName;

    private String email;

    private Address address;

    private List<PhoneNumber> phoneNumbers;

    private LocalDate creationDate;

    private LocalDate updateDate;

    public Contact() {
        id = UUID.randomUUID();
        phoneNumbers = new ArrayList<>();
        creationDate = LocalDate.now();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }

    public boolean removePhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumbers.remove(phoneNumber);
    }

    public ContactName getContactName() {
        return contactName;
    }

    public void setContactName(ContactName contactName) {
        this.contactName = contactName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContactComment() {
        return contactComment;
    }

    public void setContactComment(String contactComment) {
        this.contactComment = contactComment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getSkypeNickName() {
        return skypeNickName;
    }

    public void setSkypeNickName(String skypeNickName) {
        this.skypeNickName = skypeNickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return id.equals(contact.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public ContactGroup getContactGroup() {
        return contactGroup;
    }

    public void setContactGroup(ContactGroup contactGroup) {
        this.contactGroup = contactGroup;
    }
}

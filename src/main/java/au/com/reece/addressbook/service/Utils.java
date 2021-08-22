package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.dto.AddressBookRequestBody;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.dto.ContactRequestBody;

public class Utils {

    public static void attachAddressBookToContact(AddressBook addressBook, Contact contact) {
        contact.setAddressBook(addressBook);
    }

    public static Contact makeContactFromRequestBody(ContactRequestBody contactRequestBody) {
        Contact contact = new Contact();
        contact.setFullName(contactRequestBody.getFullName());
        contact.setMobilePhone(contactRequestBody.getMobilePhone());
        return contact;
    }

    public static AddressBook makeAddressBookFromRequestBody(AddressBookRequestBody addressBookRequestBody) {
        return AddressBook.builder()
                .name(addressBookRequestBody.getName())
                .branchNumber(addressBookRequestBody.getBranchNumber())
                .build();
    }
}

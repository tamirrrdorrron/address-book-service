package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.model.ContactRequestBody;
import au.com.reece.addressbook.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final AddressBookService addressBookService;
    private final ContactsRepository contactsRepository;
    private final ContactValidator contactValidator;

    @Autowired
    public ContactService(AddressBookService addressBookService, ContactsRepository contactsRepository, ContactValidator contactValidator) {
        this.addressBookService = addressBookService;
        this.contactsRepository = contactsRepository;
        this.contactValidator = contactValidator;
    }

    public Contact addContactToAddressBook(ContactRequestBody contactRequestBody) {
        contactValidator.validateRequestBody(contactRequestBody);
        return contactsRepository.save(convertRequestBodyToContact(contactRequestBody));
    }

    private Contact convertRequestBodyToContact(ContactRequestBody contactRequestBody) {
        AddressBook addressBook = addressBookService.getAddressBook(contactRequestBody.getAddressBookId());
        Contact contact = Utils.makeContactFromRequestBody(contactRequestBody);
        Utils.attachAddressBookToContact(addressBook, contact);
        return contact;
    }

}

package au.com.reece.addressbook.service;

import au.com.reece.addressbook.dto.ContactRequestBody;
import au.com.reece.addressbook.exceptions.ContactMismatchError;
import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.repository.ContactsRepository;
import au.com.reece.addressbook.service.validation.ContactValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ContactService {

    // need to add logging everywhere

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AddressBookService addressBookService;
    private final ContactsRepository contactsRepository;
    private final ContactValidator contactValidator;

    @Autowired
    public ContactService(AddressBookService addressBookService, ContactsRepository contactsRepository, ContactValidator contactValidator) {
        this.addressBookService = addressBookService;
        this.contactsRepository = contactsRepository;
        this.contactValidator = contactValidator;
    }

    public Contact addContactToAddressBook(int addressBookId, ContactRequestBody contactRequestBody) {
        contactValidator.validateRequestBody(contactRequestBody);
        return contactsRepository.save(convertRequestBodyToContact(addressBookId, contactRequestBody));
    }

    private Contact getContact(int contactId) {
        Optional<Contact> contact = contactsRepository.findById(contactId);
        if (contact.isPresent()) {
            return contact.get();
        } else throw new ResourceNotFoundException("no contact found for id '" + contactId + "'");
    }

    public void deleteContact(int addressBookId, int contactId) throws ContactMismatchError {
        Contact contact = getContact(contactId);
        if (contact.getAddressBook().getId() != addressBookId) {
            throw new ContactMismatchError("contact id '" + contactId + "' does not belong to address book id '" + addressBookId + "'");
        }
        contactsRepository.delete(contact);
        logger.info("contact id '" + contactId + "' deleted successfully");
    }

    private Contact convertRequestBodyToContact(int addressBookId, ContactRequestBody contactRequestBody) {
        AddressBook addressBook = addressBookService.getAddressBook(addressBookId);
        Contact contact = Utils.makeContactFromRequestBody(contactRequestBody);
        Utils.attachAddressBookToContact(addressBook, contact);
        return contact;
    }

}

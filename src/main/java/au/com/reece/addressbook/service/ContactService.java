package au.com.reece.addressbook.service;

import au.com.reece.addressbook.dto.ContactRequestBody;
import au.com.reece.addressbook.dto.ContactResponseBody;
import au.com.reece.addressbook.exceptions.ContactMismatchError;
import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.repository.ContactsRepository;
import au.com.reece.addressbook.service.validation.ContactUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactService {

    // need to add logging everywhere

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AddressBookService addressBookService;
    private final ContactsRepository contactsRepository;
    private final ContactUtils contactUtils;

    @Autowired
    public ContactService(AddressBookService addressBookService, ContactsRepository contactsRepository, ContactUtils contactUtils) {
        this.addressBookService = addressBookService;
        this.contactsRepository = contactsRepository;
        this.contactUtils = contactUtils;
    }

    public Contact addContactToAddressBook(int addressBookId, ContactRequestBody contactRequestBody) {
        contactUtils.validateRequestBody(contactRequestBody);
        contactUtils.checkIfExists(contactRequestBody.getMobilePhone(), addressBookId);
        return contactsRepository.save(convertRequestBodyToContact(addressBookId, contactRequestBody));
    }

    protected Contact getContact(int contactId) {
        Optional<Contact> contact = contactsRepository.findById(contactId);
        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new ResourceNotFoundException("no contact found for id '" + contactId + "'");
        }
    }

    public List<ContactResponseBody> getAllDistinctContacts() {
        return contactsRepository.findDistinctContact();
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
        Contact contact = makeContactFromRequestBody(contactRequestBody);
        attachAddressBookToContact(addressBook, contact);
        return contact;
    }

    private static void attachAddressBookToContact(AddressBook addressBook, Contact contact) {
        contact.setAddressBook(addressBook);
    }

    private static Contact makeContactFromRequestBody(ContactRequestBody contactRequestBody) {
        return new Contact(contactRequestBody.getFullName(), contactRequestBody.getMobilePhone());
    }


}

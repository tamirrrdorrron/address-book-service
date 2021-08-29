package au.com.reece.addressbook.service;

import au.com.reece.addressbook.exceptions.ContactMismatchError;
import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.dto.ContactRequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactServiceIT extends addressBookServiceAbstract {

    @Autowired
    ContactService contactService;

    @Test
    void shouldAddContactToAddressBook() {
        createAddressBook("a new book", "2003");
        AddressBook addressBook = addressBookService.getAllAddressBooks().get(0);

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("Tamir Doron");
        contactRequestBody.setMobilePhone("0488661490");

        contactService.addContactToAddressBook(addressBook.getId(), contactRequestBody);
        addressBook = addressBookService.getAllAddressBooks().get(0);
        Contact contact = addressBook.getContacts().iterator().next();

        assertEquals(contact.getAddressBook(), addressBook);
        assertEquals(contact.getFullName(), "Tamir Doron");
        assertEquals(contact.getMobilePhone(), "0488661490");

    }

    @Test
    void shouldThrowExceptionOnInvalidMobileNumber() {
        createAddressBook("a new book", "2003");
        AddressBook addressBook = addressBookService.getAllAddressBooks().get(0);

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("Tamir Doron");
        contactRequestBody.setMobilePhone("(02) 95008791");

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContactToAddressBook(addressBook.getId(), contactRequestBody);
        });
    }

    @Test
    void shouldNotAllowMultipleSameMobilePhoneOneAddressBook() {
        createAddressBook("a new book", "2003");
        AddressBook addressBook = addressBookService.getAllAddressBooks().get(0);

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("Tamir Doron");
        contactRequestBody.setMobilePhone("0488661490");

        contactService.addContactToAddressBook(addressBook.getId(), contactRequestBody);
        assertThrows(IllegalStateException.class, () -> {
            contactService.addContactToAddressBook(addressBook.getId(), contactRequestBody);
        });
    }

    @Test
    void shouldComplainIfTryingToDeleteValidContactButNotFromAddressBook() {
        AddressBook addressBook1 = createAddressBook("a new book", "2003");
        addressBookService.getAddressBook(addressBook1.getId());

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("Tamir Doron");
        contactRequestBody.setMobilePhone("0488661490");

        contactService.addContactToAddressBook(addressBook1.getId(), contactRequestBody);

        AddressBook addressBook2 = createAddressBook("a second new book", "2003");
        addressBookService.getAddressBook(addressBook2.getId());

        contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("John Smith");
        contactRequestBody.setMobilePhone("0488661490");

        Contact contact2 = contactService.addContactToAddressBook(addressBook2.getId(), contactRequestBody);

        assertThrows(ContactMismatchError.class, () -> {
            contactService.deleteContact(addressBook1.getId(), contact2.getId());
        });
    }

    @Test
    void shouldGetContact() {
        AddressBook addressBook1 = createAddressBook("a new book", "2003");
        addressBookService.getAddressBook(addressBook1.getId());

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("Tamir Doron");
        contactRequestBody.setMobilePhone("0488661490");

        Contact contact = contactService.addContactToAddressBook(addressBook1.getId(), contactRequestBody);

        assertTrue(contactIsTheSame(contact, contactService.getContact(contact.getId())));
    }

    @Test
    @Transactional
    void shouldDeleteContact() throws ContactMismatchError {
        AddressBook addressBook1 = createAddressBook("a new book", "2003");
        addressBookService.getAddressBook(addressBook1.getId());

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("Tamir Doron");
        contactRequestBody.setMobilePhone("0488661490");

        Contact contact = contactService.addContactToAddressBook(addressBook1.getId(), contactRequestBody);
        assertTrue(contactIsTheSame(contact, contactService.getContact(contact.getId())));

        contactService.deleteContact(addressBook1.getId(), contact.getId());

        assertThrows(ResourceNotFoundException.class, () -> {
            contactService.getContact(contact.getId());
        });
    }

    @Test
    void shouldConvertRequestBodyToContact() {}

    boolean contactIsTheSame(Contact source, Contact target) {
        return source.getAddressBook().getId() == target.getAddressBook().getId()
                && source.getFullName().equals(target.getFullName())
                && source.getMobilePhone().equals(target.getMobilePhone());
    }

}

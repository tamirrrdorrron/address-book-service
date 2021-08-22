package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.dto.ContactRequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void shouldNotAllowDuplicateContactInSameAddressBook() {
    }

    @Test
    void shouldComplainIfTryingToDeleteValidContactButNotFromAddressBook() {}

    @Test
    void shouldGetContact() {}

    @Test
    void shouldDeleteContact() {}

    @Test
    void shouldConvertRequestBodyToContact() {}

}

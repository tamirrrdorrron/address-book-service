package au.com.reece.addressbook.service;

import au.com.reece.addressbook.exceptions.ContactMismatchError;
import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.dto.ContactRequestBody;
import au.com.reece.addressbook.repository.ContactsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ContactServiceIT extends addressBookServiceAbstract {

    private static final String addressBookName = "book1";
    private static final String branchNumber = "2003";
    private static final String fullName = "David Young";
    private static final String mobilePhone = "0488661490";

    @Autowired
    ContactService contactService;
    @MockBean
    ContactsRepository contactsRepositoryMock;

    private AddressBook createAddressBookWithContact(String addressBookName, String branchNumber, String fullName, String mobilePhone) {
        AddressBook addressBook = createAddressBook(addressBookName, branchNumber);

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName(fullName);
        contactRequestBody.setMobilePhone(mobilePhone);

        contactService.addContactToAddressBook(addressBook.getId(), contactRequestBody);

        return addressBookService.getAddressBook(addressBook.getId());
    }

    @Test
    void shouldAddContactToAddressBook() {
        createAddressBookWithContact(addressBookName, branchNumber, fullName, mobilePhone);
    }

    @Test
    void shouldThrowExceptionOnInvalidMobileNumber() {
        AddressBook addressBook = createAddressBook("a new book", "2003");

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName("Tamir Doron");
        contactRequestBody.setMobilePhone("(02) 95008791");

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContactToAddressBook(addressBook.getId(), contactRequestBody);
        });
    }

    @Test
    void shouldNotAllowMultipleSameMobilePhoneOneAddressBook() {
        AddressBook addressBook = createAddressBookWithContact(addressBookName, branchNumber, fullName, mobilePhone);

        ContactRequestBody contactRequestBody = new ContactRequestBody();
        contactRequestBody.setFullName(fullName);
        contactRequestBody.setMobilePhone(mobilePhone);

        assertThrows(IllegalStateException.class, () -> {
            contactService.addContactToAddressBook(addressBook.getId(), contactRequestBody);
        });
    }

    @Test
    void shouldComplainIfTryingToDeleteValidContactButNotFromAddressBook() {
        AddressBook addressBook1 = createAddressBookWithContact("book1", branchNumber, fullName, mobilePhone);
        AddressBook addressBook2 = createAddressBookWithContact("book2", branchNumber, fullName, mobilePhone);

        assertThrows(ContactMismatchError.class, () -> {
            contactService.deleteContact(addressBook1.getId(), addressBook2.getContacts().iterator().next().getId());
        });
    }

    @Test
    void shouldGetContact() {
        AddressBook addressBook = createAddressBookWithContact(addressBookName, branchNumber, fullName, mobilePhone);
        Contact contact = addressBook.getContacts().iterator().next();

        assertEquals(contact.getFullName(), fullName);
        assertEquals(contact.getMobilePhone(), mobilePhone);
    }

    @Test
    @Transactional // this is not right to put it on the test, it should be on the service or something
    void shouldDeleteContact() throws ContactMismatchError {
        AddressBook addressBook = createAddressBookWithContact(addressBookName, branchNumber, fullName, mobilePhone);
        Contact contact = addressBookService.getAddressBook(addressBook.getId()).getContacts().iterator().next();
        contactService.deleteContact(addressBook.getId(), contact.getId());
        verify(contactsRepository, times(1)).delete(contact);
    }

    @Test
    void shouldConvertRequestBodyToContact() {}

}

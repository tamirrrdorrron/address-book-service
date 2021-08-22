package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class addressBookServiceIT extends addressBookServiceAbstract {

    @Test
    void shouldGetAllAddressBooks() {
        for (int i = 0; i < 3; i++) {
            createAddressBook("book" + i, "2009");
        }
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        assertEquals(3, addressBooks.size());
    }

    @Test
    void shouldBeAbleToCreateAddressBook() {
        addressBookService.createAddressBook(createRequestBody("address book 1", "2001"));
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        assertEquals("address book 1", addressBooks.get(0).getName());
    }

    @Test
    void shouldReturnEmptyListIfNoAddressBooksInDb() {
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        assertEquals(0, addressBooks.size());
    }

    @Test
    void shouldNotBeAbleToSaveMultipleAddressBooksWithSameName() {
        addressBookService.createAddressBook(createRequestBody("address book 2", "2001"));
        assertThrows(IllegalStateException.class, () -> {
            addressBookService.createAddressBook(createRequestBody("address book 2", "2001"));
        });
    }

    @Test
    void shouldNotBeAbleToSaveAddressBookWithInvalidBranchNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            addressBookService.createAddressBook(createRequestBody("address book 2", "abcd"));
        });
    }

    @Test
    void shouldGetAddressBookById() {
        addressBookService.createAddressBook(createRequestBody("address book 3", "2001"));
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        int id = getFirstIdFromListOfAddressBooks(addressBooks);
        assertEquals("address book 3", addressBookService.getAddressBook(id).getName());
    }


    @Test
    void shouldDeleteAddressBook() {
        int addressBookId = createAddressBook("address book to be deleted", "2001").getId();
        assertEquals(1, addressBookService.getAllAddressBooks().size());
        addressBookService.deleteAddressBook(addressBookId);
        assertEquals(0, addressBookService.getAllAddressBooks().size());
    }

    @Test
    void shouldCheckIfAddressBookExistsBeforeAttemptingDelete() {
        assertEquals(0, addressBookService.getAllAddressBooks().size());
        assertThrows(ResourceNotFoundException.class, () -> {
            addressBookService.deleteAddressBook(1);
        });
    }

    @Test
    void shouldDeleteContactsWhenDeletingAddressBook() {}

}

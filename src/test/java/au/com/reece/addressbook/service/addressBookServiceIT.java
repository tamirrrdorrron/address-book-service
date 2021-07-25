package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class addressBookServiceIT extends addressBookServiceAbstract {

    @Test
    void shouldGetAllAddressBooks() {
        createAddressBooksInDb();
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        assertEquals(3, addressBooks.size());
    }

    @Test
    void shouldBeAbleToCreateAddressBook() {
        addressBookService.createAddressBook("address book 1");
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
        addressBookService.createAddressBook("address book 2");
        addressBookService.createAddressBook("address book 2");
        addressBookService.createAddressBook("address book 2");
        assertEquals(1, addressBookService.getAllAddressBooks().size());
    }

    @Test
    void shouldGetAddressBookById() {
        addressBookService.createAddressBook("address book 3");
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        int id = getFirstIdFromListOfAddressBooks(addressBooks);
        assertEquals("address book 3", addressBookService.getAddressBook(id).getName());
    }

    private int getFirstIdFromListOfAddressBooks(List<AddressBook> addressBooks) {
        return addressBooks.iterator().next().getId();
    }

}

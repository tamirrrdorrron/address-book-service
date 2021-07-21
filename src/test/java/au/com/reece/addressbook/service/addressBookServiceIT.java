package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class addressBookServiceIT extends addressBookServiceAbstract {

    @Test
    void shouldGetAddressBooks() {
        createAddressBooksInDb();
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        assertEquals(3, addressBooks.size());
    }

    @Test
    void shouldBeAbleToSaveAddressBook() {
        addressBookService.saveAddressBook("address book 1");
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        assertEquals("address book 1", addressBooks.get(0).getName());
    }

}

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
        addressBookService.createAddressBook("address book 1");
        addressBookService.createAddressBook("address book 1");
        addressBookService.createAddressBook("address book 1");
        assertEquals(1, addressBookService.getAllAddressBooks().size());
    }

//    @Test
//    void shouldBeAbleToAddAContactToAnAddressBook() {
//        // create a new book
//        addressBookService.createAddressBook("test book");
//        AddressBook addressBook = addressBookService.getAllAddressBooks().get(0);
//
//        // create and save new pages
//        Contact contact = new Contact();
//        contact.setId(1);
//        contact.setMobilePhone("123445");
//        contact.setFullName("hannah");
//        contact.setAddressBook(addressBook);
//        addressBookService.createContact(contact);
//
//        // create and save new pages
//        contact = new Contact();
//        contact.setId(2);
//        contact.setMobilePhone("gg45");
//        contact.setFullName("hannadfdfh");
//        contact.setAddressBook(addressBook);
//        addressBookService.createContact(contact);
//
//        System.out.println("debug here");
//    }

}

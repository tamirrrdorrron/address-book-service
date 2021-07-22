package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
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
    void shouldBeAbleToSaveAddressBook() {
        addressBookService.saveAddressBook("address book 1");
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();
        assertEquals("address book 1", addressBooks.get(0).getAddress_book_name());
    }

    @Test
    void shouldNotBeAbleToSaveMultipleAddressBooksWithSameName() {}

    @Test
    void shouldBeAbleToAddAContactToAnAddressBook() {
        // create a new book
        AddressBook ab = new AddressBook();
        ab.setAddress_book_id(1);
        ab.setAddress_book_name("tamirrr");

        // save the book
        addressBooksRepository.save(ab);

        // create and save new pages
        Contact contact = new Contact();
        contact.setContact_id(1);
        contact.setContact_mobile_phone("123445");
        contact.setContact_full_name("hannah");
        contact.setAddressBook(ab);
        contactsRepository.save(contact);

        // create and save new pages
        contact = new Contact();
        contact.setContact_id(2);
        contact.setContact_mobile_phone("gg45");
        contact.setContact_full_name("hannadfdfh");
        contact.setAddressBook(ab);
        contactsRepository.save(contact);

        System.out.println("debug here");
    }

}

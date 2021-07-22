package au.com.reece.addressbook.service;

import au.com.reece.addressbook.repository.AddressBooksRepository;
import au.com.reece.addressbook.repository.ContactsRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

public class addressBookServiceAbstract {

    @Autowired
    AddressBookService addressBookService;
    @Autowired
    AddressBooksRepository addressBooksRepository;
    @Autowired
    ContactsRepository contactsRepository;

    void createAddressBooksInDb() {
        addressBookService.saveAddressBook("first book");
        addressBookService.saveAddressBook("second book");
        addressBookService.saveAddressBook("third book");
    }

    @AfterEach
    void deleteAllAddressBooks() {
        addressBooksRepository.deleteAll();
    }

}

package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBookRequestBody;
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

    void createAddressBook(String name, String branchNumber) {
        addressBookService.createAddressBook(createRequestBody(name, branchNumber));
    }

    @AfterEach
    void deleteAllAddressBooks() {
        addressBooksRepository.deleteAll();
    }

    public AddressBookRequestBody createRequestBody(String name, String branchNumber) {
        AddressBookRequestBody addressBookRequestBody = new AddressBookRequestBody();
        addressBookRequestBody.setName(name);
        addressBookRequestBody.setBranchNumber(branchNumber);
        return addressBookRequestBody;

    }

}

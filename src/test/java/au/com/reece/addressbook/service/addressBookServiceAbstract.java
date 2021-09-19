package au.com.reece.addressbook.service;

import au.com.reece.addressbook.dto.AddressBookRequestBody;
import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class addressBookServiceAbstract {

    @Autowired
    AddressBookService addressBookService;
    @Autowired
    AddressBooksRepository addressBooksRepository;

    @AfterEach
    void deleteAllAddressBooks() {
        addressBooksRepository.deleteAll();
    }

    public AddressBookRequestBody createRequestBody(String name, String branchNumber) {
        return AddressBookRequestBody.builder()
                .name(name)
                .branchNumber(branchNumber)
                .build();
    }

    protected AddressBook createAddressBook(String name, String branchNumber) {
        return addressBookService.createAddressBook(createRequestBody(name, branchNumber));
    }

    protected int getFirstIdFromListOfAddressBooks(List<AddressBook> addressBooks) {
        return addressBooks.iterator().next().getId();
    }

}

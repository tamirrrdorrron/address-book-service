package au.com.reece.addressbook.service;

import au.com.reece.addressbook.dto.AddressBookRequestBody;
import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import au.com.reece.addressbook.service.validation.AddressBookUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    // need to add logging everywhere

    private final AddressBooksRepository addressBooksRepository;
    private final AddressBookUtils addressBookUtils;

    @Autowired
    public AddressBookService(AddressBooksRepository addressBooksRepository, AddressBookUtils addressBookUtils) {
        this.addressBooksRepository = addressBooksRepository;
        this.addressBookUtils = addressBookUtils;
    }

    public AddressBook getAddressBook(int addressBookId) {
        if (addressBooksRepository.findById(addressBookId).isPresent()) {
            return addressBooksRepository.findById(addressBookId).get();
        } else {
            throw new ResourceNotFoundException("no address book found for id '" + addressBookId + "'");
        }
    }

    public List<AddressBook> getAllAddressBooks() {
        return (List<AddressBook>) addressBooksRepository.findAll();
    }

    public AddressBook createAddressBook(AddressBookRequestBody addressBookRequestBody) {
        addressBookUtils.validateRequestBody(addressBookRequestBody);  // try spring boot validation
        addressBookUtils.checkIfExists(addressBookRequestBody.getName(), addressBookRequestBody.getBranchNumber());
        return addressBooksRepository.save(makeAddressBookFromRequestBody(addressBookRequestBody));
        }

    public void deleteAddressBook(int addressBookId) {
        if (addressBooksRepository.findById(addressBookId).isPresent()) {
            addressBooksRepository.deleteById(addressBookId);
        } else {
            throw new ResourceNotFoundException("no address book found for id '" + addressBookId + "'");
        }
    }

    public static AddressBook makeAddressBookFromRequestBody(AddressBookRequestBody addressBookRequestBody) {
        return AddressBook.builder()
                .name(addressBookRequestBody.getName())
                .branchNumber(addressBookRequestBody.getBranchNumber())
                .build();
    }
}

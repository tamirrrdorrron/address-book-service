package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.AddressBookRequestBody;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    private final AddressBooksRepository addressBooksRepository;
    private final AddressBookValidator addressBookValidator;

    @Autowired
    public AddressBookService(AddressBooksRepository addressBooksRepository, AddressBookValidator addressBookValidator) {
        this.addressBooksRepository = addressBooksRepository;
        this.addressBookValidator = addressBookValidator;
    }

    public AddressBook getAddressBook(int addressBookId) {
        if (addressBooksRepository.findById(addressBookId).isPresent()) {
            return addressBooksRepository.findById(addressBookId).get();
        } else return null;
    }

    public List<AddressBook> getAllAddressBooks() {
        return (List<AddressBook>) addressBooksRepository.findAll();
    }

    public AddressBook createAddressBook(AddressBookRequestBody addressBookRequestBody) throws IllegalArgumentException {
        addressBookValidator.validateRequestBody(addressBookRequestBody);
        addressBookValidator.checkIfExists(addressBookRequestBody.getName(), addressBookRequestBody.getBranchNumber());
        return addressBooksRepository.save(Utils.makeAddressBookFromRequestBody(addressBookRequestBody));
        }
}

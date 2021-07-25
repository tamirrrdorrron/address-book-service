package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    private final AddressBooksRepository addressBooksRepository;

    @Autowired
    public AddressBookService(AddressBooksRepository addressBooksRepository) {
        this.addressBooksRepository = addressBooksRepository;
    }

    public AddressBook getAddressBook(int addressBookId) {
        if (addressBooksRepository.findById(addressBookId).isPresent()) {
            return addressBooksRepository.findById(addressBookId).get();
        } else return null;
    }

    public List<AddressBook> getAllAddressBooks() {
        return (List<AddressBook>) addressBooksRepository.findAll();
    }

    public AddressBook createAddressBook(String name) {
        if (checkIfExists(name)) {
            return null;
        } else {
            AddressBook addressBook = new AddressBook();
            addressBook.setName(name);
            return addressBooksRepository.save(addressBook);
        }
    }

    private boolean checkIfExists(String name) {
        return addressBooksRepository.findByName(name).size() > 0;
    }
}

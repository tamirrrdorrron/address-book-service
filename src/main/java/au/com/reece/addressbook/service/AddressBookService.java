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
    public AddressBookService (AddressBooksRepository addressBooksRepository) {
        this.addressBooksRepository = addressBooksRepository;
    }

    public String createAddressBook(String name) {
        if (checkIfExists(name)) {
            return "duplicate found";
        } else {
            AddressBook addressBook = new AddressBook();
            addressBook.setName(name);
            addressBooksRepository.save(addressBook);
            return "saved";
        }
    }

    public List<AddressBook> getAllAddressBooks() {
        return (List<AddressBook>) addressBooksRepository.findAll();
    }

    private boolean checkIfExists(String name) {
        return addressBooksRepository.findByName(name).size() > 0;
    }
}

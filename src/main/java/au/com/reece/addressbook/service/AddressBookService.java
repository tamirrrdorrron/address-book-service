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

    public String saveAddressBook(String name) {
        AddressBook addressBook = new AddressBook();
        addressBook.setAddress_book_name(name);
        addressBooksRepository.save(addressBook);
        return "Saved";
    }

    public List<AddressBook> getAllAddressBooks() {
        return (List<AddressBook>) addressBooksRepository.findAll();
    }
}

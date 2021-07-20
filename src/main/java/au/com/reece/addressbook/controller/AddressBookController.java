package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBooksRepository addressBooksRepository;

    @PostMapping(path="/address-book/add")
    public @ResponseBody String addAddressBooks (@RequestParam String name) {
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        addressBooksRepository.save(addressBook);
        return "Saved";
    }


    @GetMapping(path="/address-book/all")
    public @ResponseBody Iterable<AddressBook> getAllAddressBooks() {
        return addressBooksRepository.findAll();
    }
}

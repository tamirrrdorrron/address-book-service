package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import au.com.reece.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping(path="/address-book/add")
    public @ResponseBody String addAddressBooks (@RequestParam String name) {
        return addressBookService.saveAddressBook(name);
    }


    @GetMapping(path="/address-book/all")
    public @ResponseBody
    List<AddressBook> getAllAddressBooks() {
        return addressBookService.getAllAddressBooks();
    }
}

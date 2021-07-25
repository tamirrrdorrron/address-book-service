package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.model.ContactRequestBody;
import au.com.reece.addressbook.service.AddressBookService;
import au.com.reece.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private ContactService contactService;

    @PostMapping(path="/address-book/add")
    public @ResponseBody AddressBook addAddressBooks (@RequestParam String name) {
        return addressBookService.createAddressBook(name);
    }

    @PostMapping(path="/address-book/contact/add")
    public @ResponseBody Contact addContactToAddressBook (@RequestBody ContactRequestBody contactRequestBody) {
        return contactService.addContactToAddressBook(contactRequestBody);
    }

    @GetMapping(path="/address-book/all")
    public @ResponseBody
    List<AddressBook> getAllAddressBooks() {
        return addressBookService.getAllAddressBooks();
    }
}

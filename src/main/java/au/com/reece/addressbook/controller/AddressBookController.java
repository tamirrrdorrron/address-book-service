package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.AddressBookRequestBody;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.model.ContactRequestBody;
import au.com.reece.addressbook.service.AddressBookService;
import au.com.reece.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private ContactService contactService;

    @PostMapping(path="/address-book/add")
    public @ResponseBody AddressBook addAddressBooks (@RequestBody AddressBookRequestBody addressBookRequestBody) {
        return addressBookService.createAddressBook(addressBookRequestBody);
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

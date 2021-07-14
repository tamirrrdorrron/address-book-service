package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping(value = "/address-books", method = RequestMethod.GET)
    public ResponseEntity listAddressBooks() {
        List<AddressBook> addressBooks = addressBookService.getAddressBooks();
        return new ResponseEntity(addressBooks, HttpStatus.OK);
    }
}

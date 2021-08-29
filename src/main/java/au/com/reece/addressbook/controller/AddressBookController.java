package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.dto.AddressBookRequestBody;
import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
@RequestMapping("/address-book")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping(path="/")
    public @ResponseBody
    List<AddressBook> getAllAddressBooks() {
        return addressBookService.getAllAddressBooks();
    }

    @PostMapping(path="/add")
    public @ResponseBody AddressBook addAddressBooks (@Valid @RequestBody AddressBookRequestBody addressBookRequestBody) {
        return addressBookService.createAddressBook(addressBookRequestBody);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody
    AddressBook getAddressBook(
            @Pattern(regexp = "^[0-9]\\d*$", message = "address book id must be a integer >= 0")
            @PathVariable("id") String id) {
        return addressBookService.getAddressBook(Integer.parseInt(id));
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> deleteAddressBook(
            @Pattern(regexp = "^[0-9]\\d*$", message = "address book id must be a integer >= 0")
            @PathVariable("id") String id) {
        addressBookService.deleteAddressBook(Integer.parseInt(id));
        return ResponseEntity.ok("{\n\"message\": \"address book with id '" + id + "' deleted successfully\"\n}");
    }
}

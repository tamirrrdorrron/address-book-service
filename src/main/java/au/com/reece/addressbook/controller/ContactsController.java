package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.dto.ContactRequestBody;
import au.com.reece.addressbook.exceptions.ContactMismatchError;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@Controller
@Validated
@RequestMapping("/address-book")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @PostMapping(path="/{addressBookId}/contact/add")
    public @ResponseBody
    Contact addContactToAddressBook (
            @Pattern(regexp = "^[0-9]\\d*$", message = "address book id must be a integer >= 0")
            @PathVariable("addressBookId") String addressBookId,
            @RequestBody ContactRequestBody contactRequestBody
    ) {
        return contactService.addContactToAddressBook(Integer.parseInt(addressBookId), contactRequestBody);
    }

    @DeleteMapping(path="/{addressBookId}/contact/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> deleteContactFromAddressBook (
            @Pattern(regexp = "^[0-9]\\d*$", message = "address book id must be a integer >= 0")
            @PathVariable("addressBookId") String addressBookId,
            @Pattern(regexp = "^[0-9]\\d*$", message = "address book id must be a integer >= 0")
            @PathVariable("contactId") String contactId
    ) throws ContactMismatchError {
        contactService.deleteContact(Integer.parseInt(addressBookId), Integer.parseInt(contactId));
        return ResponseEntity.ok("{\n\"message\": \"contact id '" + contactId + "' deleted successfully from address book '" + addressBookId + "'\"\n}");
    }

}

package au.com.reece.addressbook.service.validation;

import au.com.reece.addressbook.dto.ContactRequestBody;
import au.com.reece.addressbook.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactUtils {

    @Autowired
    ContactsRepository contactsRepository;

    public void validateRequestBody(ContactRequestBody contactRequestBody) {
        MobilePhoneUtils.validateMobileNumber(contactRequestBody.getMobilePhone());
    }

    public void checkIfExists(String mobilePhone, int addressBookId) {
        if (contactsRepository.existsContactByAddressBook_IdAndMobilePhone(addressBookId, mobilePhone)) {
            throw new IllegalStateException("contact with mobile phone '" + mobilePhone + "' already exists in address book '" + addressBookId + "'");
        }
    }

}

package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.ContactRequestBody;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    public void validateRequestBody(ContactRequestBody contactRequestBody) throws IllegalArgumentException {
        MobilePhoneValidator.validateMobileNumber(contactRequestBody.getMobilePhone());
    }

}

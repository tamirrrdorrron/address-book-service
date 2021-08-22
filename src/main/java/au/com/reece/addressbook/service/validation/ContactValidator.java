package au.com.reece.addressbook.service.validation;

import au.com.reece.addressbook.dto.ContactRequestBody;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    public void validateRequestBody(ContactRequestBody contactRequestBody) throws IllegalArgumentException {
        MobilePhoneValidator.validateMobileNumber(contactRequestBody.getMobilePhone());
    }

}

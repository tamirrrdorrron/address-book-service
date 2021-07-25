package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBookRequestBody;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AddressBookValidator {

    @Autowired
    AddressBooksRepository addressBooksRepository;

    public void validateRequestBody(AddressBookRequestBody addressBookRequestBody) throws IllegalArgumentException {
        BranchValidator.validateBranchNumber(addressBookRequestBody.getBranchNumber());
    }

    public void checkIfExists(String name, String branchNumber) {
        if (addressBooksRepository.findByNameAndBranchNumber(name, branchNumber).size() > 0) {
            throw new IllegalStateException("address book already exists");
        };
    }

}

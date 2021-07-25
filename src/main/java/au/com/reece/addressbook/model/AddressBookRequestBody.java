package au.com.reece.addressbook.model;

import lombok.Data;

@Data
public class AddressBookRequestBody {
    private String name;
    private String branchNumber;
}

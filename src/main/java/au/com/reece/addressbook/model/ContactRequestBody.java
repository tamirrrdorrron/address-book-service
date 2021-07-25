package au.com.reece.addressbook.model;

import lombok.Data;

@Data
public class ContactRequestBody {

    private String fullName;
    private String mobilePhone;
    private int addressBookId;
}

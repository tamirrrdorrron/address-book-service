package au.com.reece.addressbook.dto;

import lombok.Data;

@Data
public class ContactRequestBody {

    private String fullName;
    private String mobilePhone;

}

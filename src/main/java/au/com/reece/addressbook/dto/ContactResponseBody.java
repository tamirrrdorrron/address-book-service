package au.com.reece.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactResponseBody {
    private String fullName;
    private String mobilePhone;
}

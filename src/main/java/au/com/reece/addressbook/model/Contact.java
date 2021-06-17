package au.com.reece.addressbook.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Contact {

    int id;
    String name;
    String phoneNumber;
}

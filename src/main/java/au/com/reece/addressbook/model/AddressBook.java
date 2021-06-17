package au.com.reece.addressbook.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AddressBook {

    int id;
    String name;
    List<Contact> contacts;
}

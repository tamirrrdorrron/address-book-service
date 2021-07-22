package au.com.reece.addressbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class AddressBook {

    @Column
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int address_book_id;

    @Column
    String address_book_name;

    @OneToMany(mappedBy="addressBook", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Contact> contacts;
}

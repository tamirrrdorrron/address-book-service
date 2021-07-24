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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @OneToMany(mappedBy="addressBook", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Contact> contacts;
}

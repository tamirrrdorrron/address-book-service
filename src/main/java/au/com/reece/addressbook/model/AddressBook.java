package au.com.reece.addressbook.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class AddressBook {

    public AddressBook() {
    }

    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    String branchNumber;

    @OneToMany(mappedBy="addressBook", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Contact> contacts;
}

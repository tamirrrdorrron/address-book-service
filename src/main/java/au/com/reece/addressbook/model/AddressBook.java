package au.com.reece.addressbook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class AddressBook {

    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @SuppressWarnings("PMD.ShortVariable")
    int id;

    @Column
    String name;

    @Column
    String branchNumber;

    @OneToMany(mappedBy="addressBook", fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Contact> contacts;

    @Builder
    public AddressBook(String name, String branchNumber) {
        this.name = name;
        this.branchNumber = branchNumber;
    }

    public AddressBook() {
    }

}

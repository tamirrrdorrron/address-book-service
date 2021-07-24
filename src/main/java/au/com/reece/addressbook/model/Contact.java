package au.com.reece.addressbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column
    String fullName;

    @Column
    String mobilePhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="address_book_id", nullable=false)
    private AddressBook addressBook;
}

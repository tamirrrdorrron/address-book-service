package au.com.reece.addressbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Contact {

    @Column
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int contact_id;

    @Column
    String contact_full_name;

    @Column
    String contact_mobile_phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="contact_address_book_id", nullable=false)
    private AddressBook addressBook;
}

package au.com.reece.addressbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "addressBook"})
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column
    String fullName;

    @Column
    String mobilePhone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="address_book_id", nullable=false)
    AddressBook addressBook;
}

package au.com.reece.addressbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
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
    @SuppressWarnings("PMD.ShortVariable")
    int id;

    @Column
    String fullName;

    @Column
    String mobilePhone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="address_book_id", nullable=false)
    AddressBook addressBook;

    public Contact() {}

    public Contact(String fullName, String mobilePhone) {
        this.fullName = fullName;
        this.mobilePhone = mobilePhone;
    }

    @Builder
    public Contact(String fullName, String mobilePhone, AddressBook addressBook) {
        this.fullName = fullName;
        this.mobilePhone = mobilePhone;
        this.addressBook = addressBook;
    }

}

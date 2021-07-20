package au.com.reece.addressbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}

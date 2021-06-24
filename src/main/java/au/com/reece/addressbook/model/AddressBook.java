package au.com.reece.addressbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class AddressBook {

    @Column
    @Id
    int id;

    @Column
    String name;
}

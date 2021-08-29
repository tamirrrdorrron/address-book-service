package au.com.reece.addressbook.repository;

import au.com.reece.addressbook.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactsRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findContactByMobilePhoneAndAddressBook_Id(String mobilePhone, int addressBookId);
}

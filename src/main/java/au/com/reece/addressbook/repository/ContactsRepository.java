package au.com.reece.addressbook.repository;

import au.com.reece.addressbook.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactsRepository extends CrudRepository<Contact, Integer> {
}

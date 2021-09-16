package au.com.reece.addressbook.repository;

import au.com.reece.addressbook.dto.ContactResponseBody;
import au.com.reece.addressbook.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactsRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findContactByMobilePhoneAndAddressBook_Id(String mobilePhone, int addressBookId);
    boolean existsContactByAddressBook_IdAndMobilePhone(int addressBookId, String mobilePhone);

    @Query("SELECT DISTINCT new au.com.reece.addressbook.dto.ContactResponseBody (c.fullName, c.mobilePhone) FROM Contact c")
    List<ContactResponseBody> findDistinctContact();
}

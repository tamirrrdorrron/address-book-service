package au.com.reece.addressbook.dao;

import au.com.reece.addressbook.model.AddressBook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AddressBookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AddressBook> getAddressBooks() {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(AddressBook.class);
        return criteria.list();
    }

}

package au.com.reece.addressbook.service;

import au.com.reece.addressbook.model.AddressBook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;


@SpringBootTest
public class addressBookServiceTest extends addressBookServiceAbstract {

    @Autowired
    EntityManager entityManager;

    @Test
    void shouldGetAddressBooks() {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(AddressBook.class);
        System.out.println("123");
        criteria.list();

    }
}

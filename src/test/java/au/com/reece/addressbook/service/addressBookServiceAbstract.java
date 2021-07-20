package au.com.reece.addressbook.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class addressBookServiceAbstract {

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void init() {
        createAddressBooksInDb();
    }

    void createAddressBooksInDb() {
        entityManager.createNativeQuery("INSERT INTO Address_Book (id, name) VALUES (1, 'first address book');");
    }

}

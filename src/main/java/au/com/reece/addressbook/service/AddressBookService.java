package au.com.reece.addressbook.service;

import au.com.reece.addressbook.dao.AddressBookDao;
import au.com.reece.addressbook.model.AddressBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookDao addressBookDao;

    public List<AddressBook> getAddressBooks() {
        return addressBookDao.getAddressBooks();
    }
}

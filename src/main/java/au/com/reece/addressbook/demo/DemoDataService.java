package au.com.reece.addressbook.demo;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.model.Contact;
import au.com.reece.addressbook.repository.AddressBooksRepository;
import au.com.reece.addressbook.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("development")
@Service
public class DemoDataService {

    @Autowired
    AddressBooksRepository addressBooksRepository;
    @Autowired
    ContactsRepository contactsRepository;

    public String initialize() {
        AddressBook addressBook;
        addressBook = addressBooksRepository.save(AddressBook.builder().branchNumber("2001").name("my nice customers").build());
        contactsRepository.save(Contact.builder().fullName("Tamir Doron").mobilePhone("0488661490").addressBook(addressBook).build());

        addressBook = addressBooksRepository.save(AddressBook.builder().branchNumber("2001").name("my not so nice customers").build());
        contactsRepository.save(Contact.builder().fullName("Erez Yonash").mobilePhone("0412922884").addressBook(addressBook).build());

        addressBook = addressBooksRepository.save(AddressBook.builder().branchNumber("2002").name("burwood VIP customers").build());
        contactsRepository.save(Contact.builder().fullName("Viktor Mikho").mobilePhone("0412922885").addressBook(addressBook).build());
        contactsRepository.save(Contact.builder().fullName("Sonam Singh").mobilePhone("0412922886").addressBook(addressBook).build());

        return "initialized test data";
    }

    public String cleanup() {
        addressBooksRepository.deleteAll();
        return "cleaned up test data";
    }

}

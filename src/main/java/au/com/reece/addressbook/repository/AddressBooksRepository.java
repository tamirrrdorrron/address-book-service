package au.com.reece.addressbook.repository;
import org.springframework.data.repository.CrudRepository;
import au.com.reece.addressbook.model.AddressBook;

import java.util.List;


public interface AddressBooksRepository extends CrudRepository<AddressBook, Integer> {
    List<AddressBook> findByNameAndBranchNumber(String name, String branchNumber);
    boolean existsAddressBookByNameAndBranchNumber(String name, String branchNumber);
}
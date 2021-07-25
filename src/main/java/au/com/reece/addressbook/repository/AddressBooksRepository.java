package au.com.reece.addressbook.repository;
import org.springframework.data.repository.CrudRepository;
import au.com.reece.addressbook.model.AddressBook;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AddressBooksRepository extends CrudRepository<AddressBook, Integer> {
    List<AddressBook> findByNameAndBranchNumber(String name, String branchNumber);
}
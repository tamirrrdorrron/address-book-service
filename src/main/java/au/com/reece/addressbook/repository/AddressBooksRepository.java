package au.com.reece.addressbook.repository;
import org.springframework.data.repository.CrudRepository;
import au.com.reece.addressbook.model.AddressBook;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AddressBooksRepository extends CrudRepository<AddressBook, Integer> {
}
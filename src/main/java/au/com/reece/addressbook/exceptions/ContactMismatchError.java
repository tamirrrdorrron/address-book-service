package au.com.reece.addressbook.exceptions;

public class ContactMismatchError extends Exception {
    public ContactMismatchError(String errorMessage) {
        super(errorMessage);
    }
}

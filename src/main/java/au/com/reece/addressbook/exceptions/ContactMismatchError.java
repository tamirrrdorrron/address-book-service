package au.com.reece.addressbook.exceptions;

public class ContactMismatchError extends Exception {
    private static final long serialVersionUID = -7084364539507022573L;

    public ContactMismatchError(String errorMessage) {
        super(errorMessage);
    }
}

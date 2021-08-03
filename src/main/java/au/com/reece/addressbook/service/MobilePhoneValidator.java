package au.com.reece.addressbook.service;

public class MobilePhoneValidator {

    private static final String mobilePhonePattern = "^(?:\\(?(?:\\+?61|0)4\\)?(?:[ -]?[0-9]){7}[0-9])";

    public static void validateMobileNumber(String mobilePhone) {
        if (!mobilePhone.matches(mobilePhonePattern)) {
            throw new IllegalArgumentException("invalid phone number");
        };
    }

}

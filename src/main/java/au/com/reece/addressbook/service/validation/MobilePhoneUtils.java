package au.com.reece.addressbook.service.validation;

public class MobilePhoneUtils {

    private static final String MOBILE_PHONE_PATTERN = "^(?:\\(?(?:\\+?61|0)4\\)?(?:[ -]?[0-9]){7}[0-9])";

    public static void validateMobileNumber(String mobilePhone) {
        if (!mobilePhone.matches(MOBILE_PHONE_PATTERN)) {
            throw new IllegalArgumentException("invalid phone number");
        }
    }

}

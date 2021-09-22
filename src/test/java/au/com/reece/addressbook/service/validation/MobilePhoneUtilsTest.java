package au.com.reece.addressbook.service.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MobilePhoneUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"234 3450 234", "a234 534 3432", "134567", "123456789013"}) // invalid mobile numbers
    void shouldThrowExceptionOnInvalidMobileNumber(String mobileNumber) {
        assertThrows(IllegalArgumentException.class, () -> {
            MobilePhoneUtils.validateMobileNumber(mobileNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0412345678", "+61412345678", "0412 345 678"}) // valid mobile numbers
    void shouldAllowValidMobileNumber(String mobileNumber) {
        MobilePhoneUtils.validateMobileNumber(mobileNumber);
    }
}
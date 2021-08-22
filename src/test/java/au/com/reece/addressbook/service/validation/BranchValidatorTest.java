package au.com.reece.addressbook.service.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BranchValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"abcd", "20001", "1", "12", "123", "12AB"}) // invalid branch numbers
    void shouldThrowExceptionOnInvalidBranchNumber(String branchNumber) {
        assertThrows(IllegalArgumentException.class, () -> {
            BranchValidator.validateBranchNumber(branchNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"2000", "1000", "1001", "9001"}) // valid branch numbers
    void shouldAllowValidBranchNumber(String branchNumber) {
        BranchValidator.validateBranchNumber(branchNumber);
    }
}

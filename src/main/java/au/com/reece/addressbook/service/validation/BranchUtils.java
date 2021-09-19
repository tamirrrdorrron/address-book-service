package au.com.reece.addressbook.service.validation;

public class BranchUtils {

    private static final String BRANCH_NUMBER_PATTERN = "^[0-9]{4}$";

    public static void validateBranchNumber(String branchNumber) {
        if (!branchNumber.matches(BRANCH_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Branch number must be a 4 digit number");
        }
    }

}

package au.com.reece.addressbook.service.validation;

public class BranchValidator {

    private static final String branchNumberPattern = "^[0-9]{4}$";

    public static void validateBranchNumber(String branchNumber) {
        if (!branchNumber.matches(branchNumberPattern)) {
            throw new IllegalArgumentException("Branch number must be a 4 digit number");
        };
    }

}

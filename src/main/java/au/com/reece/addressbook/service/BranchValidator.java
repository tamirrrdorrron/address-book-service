package au.com.reece.addressbook.service;

public class BranchValidator {

    private static final String branchNumberPattern = "^[0-9]{4}$";

    public static void validateBranchNumber(String branchNumber) {
        if (!branchNumber.matches(branchNumberPattern)) {
            throw new IllegalArgumentException("invalid branch number");
        };
    }

}

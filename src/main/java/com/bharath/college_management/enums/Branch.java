package com.bharath.college_management.enums;

public enum Branch {
    CSE, MME, ME, ECE, EEE, CD, IT;

    public static boolean isValidBranch(String branch) {
        for (Branch b : Branch.values()) {
            if(b.name().equalsIgnoreCase(branch)) {
                return true;
            }
        }
        return false;
    }
}

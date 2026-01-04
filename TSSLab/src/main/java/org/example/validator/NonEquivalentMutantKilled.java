package org.example.validator;

public class NonEquivalentMutantKilled {
    public double calculateBonus(int sales, int absentDays,  boolean isManager) {
        if (sales < 1000) {
            return 0.0; // no bonus :(
        } else if (sales <= 5000) {
            return sales * 0.6; // change here, mutant killed
        } else {
            // if sales > 5000
            if (absentDays < 3 || isManager) {
                return sales * 0.10; // bonus 10%
            } else {
                return sales * 0.07; // bonus 7%
            }
        }
    }
}

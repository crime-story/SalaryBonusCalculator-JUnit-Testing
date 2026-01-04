package org.example.validator;

public class SalaryBonusCalculator {
     /*
     * Calculeaza bonusul unui angajat pe baza vanzarilor (sales) si a statusului.
     * * Specificatie:
     * 1. sales < 1000 -> Bonus 0
     * 2. sales [1000 - 5000] -> Bonus 5%
     * 3. sales > 5000:
     * a. Daca (absentDays < 3 SAU isManager == true) -> Bonus 10%
     * b. Altfel -> Bonus 7%
     */
    public double calculateBonus(int sales, int absentDays,  boolean isManager) {
        if (sales < 1000) {
            return 0.0; // no bonus :(
        } else if (sales <= 5000) {
            return sales * 0.05; // bonus 5%
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

package org.example.validator;

public class EquivalentMutant {
    public double calculateBonus(int sales, int absentDays,  boolean isManager) {
        if (sales < 1000) {
            return 0.0; // no bonus :(
        } else if (sales <= 5000) {
            return sales * 0.5; // bonus 5%
        } else {
            // if sales > 5000
            // Modificam < in <= si scadem valoarea cu 1.
            // Pentru numere intregi, "mai mic decat 3" e fix acelasi lucru cu "mai mic sau egal cu 2".
            if (absentDays <= 2 || isManager) {
                return sales * 0.10; // bonus 10%
            } else {
                return sales * 0.07; // bonus 7%
            }
        }
    }
}

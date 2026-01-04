package validator;

import org.example.validator.SalaryBonusCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquivalencePartitioningTest {
    SalaryBonusCalculator calculator = new SalaryBonusCalculator();

    // Clase echivalente identificate:
    // 1. Low Sales (<1000)
    // 2. Average Sales (1000 - 5000)
    // 3. Big Sales (>5000) cu performanta buna (absente putine sau manager)
    // 4. Big Sales (>5000) cu performanta slaba (absente multe si non-manager)

    @Test
    void testEP_LowSales() {
        // Clasa: < 1000. Alegem valoarea 500.
        assertEquals(0.0, calculator.calculateBonus(500, 0, false), 0.01);
    }

    @Test
    void testEP_AverageSales() {
        // Clasa: [1000, 5000]. Alegem 2500. Bonus 5% (125).
        assertEquals(125.0, calculator.calculateBonus(2500, 5, false), 0.01);
    }

    @Test
    void testEP_BigSales_BiggestBonus() {
        // Clasa: > 5000, Absente < 3. Alegem 6000, 1 absenta. Bonus 10% (600).
        assertEquals(600.0, calculator.calculateBonus(6000, 1, false), 0.01);
    }

    @Test
    void test_Coverage_ManagerWithTooManyAbsentDays() {
        // Sales > 5000 (ca sa ajungem la ultimul if)
        // AbsentDays = 4 (ca prima conditie "absente < 3" sa fie FALSE)
        // isManager = true (ca sa verificam ramura de TRUE a variabilei isManager)

        // Rezultatul trebuie sa fie 10% (600.0) pentru ca e Manager, chiar daca a lipsit.
        assertEquals(600.0, calculator.calculateBonus(6000, 4, true), 0.01);
    }

    @Test
    void testEP_BigSales_LowBonus() {
        // Clasa: > 5000, Absente >= 3, Non-Manager. Alegem 6000, 5 absente. Bonus 7% (420).
        // Adauga 0.01 la final. Asta inseamna ca accepti o diferenta mai mica de 0.01
        // Am folosit delta pentru a compensa erorile de precizie specifice numerelor in virgula mobila (floating-point).
        assertEquals(420.0, calculator.calculateBonus(6000, 5, false), 0.01);
    }
}

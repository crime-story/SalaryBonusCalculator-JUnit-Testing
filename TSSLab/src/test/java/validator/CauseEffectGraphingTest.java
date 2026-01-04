package validator;

import org.example.validator.SalaryBonusCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CauseEffectGraphingTest {
    SalaryBonusCalculator calculator = new SalaryBonusCalculator();

    // Cazul 1: C1 -> E1
    @Test
    void testCEG_LowSales() {
        // C1 este True (500 < 1000)
        // Restul nu conteaza
        assertEquals(0.0, calculator.calculateBonus(500, 0, false), 0.01);
    }

    // Cazul 2: C2 -> E2
    @Test
    void testCEG_AverageSales() {
        // C2 este True (2000 este intre 1000-5000)
        // 2000 * 5% = 100
        assertEquals(100.0, calculator.calculateBonus(2000, 0, false), 0.01);
    }

    // Cazul 3: C3 + C4 -> E3
    // Vanzari Mari + Absente Putine + Nu e Manager
    @Test
    void testCEG_BigSales_LowAbsentDays() {
        // C3 True (>5000), C4 True (<3 abs), C5 False
        // 6000 * 10% = 600
        assertEquals(600.0, calculator.calculateBonus(6000, 1, false), 0.01);
    }

    // Cazul 4: C3 + (NOT C4) + C5 -> E3
    // Vanzari Mari + Absente Multe + ESTE Manager
    @Test
    void testCEG_BigSales_isManager_TooManyAbsentDays() {
        // C3 True, C4 False (5 absente), C5 True (Manager)
        // Logica: Chiar daca are absente, fiind manager ia 10%
        // 6000 * 10% = 600
        assertEquals(600.0, calculator.calculateBonus(6000, 5, true), 0.01);
    }

    // Cazul 5: C3 + C4 + C5 -> E3
    // Vanzari Mari + Absente Putine + ESTE Manager (Bonus dublu asigurat, tot 10% ia)
    @Test
    void testCEG_BigSales_isManager_LowAbsentDays() {
        // C3 True, C4 True, C5 True
        // 6000 * 10% = 600
        assertEquals(600.0, calculator.calculateBonus(6000, 1, true), 0.01);
    }

    // Cazul 6: C3 + (NOT C4) + (NOT C5) -> E4
    // Vanzari Mari + Absente Multe + NU e Manager
    @Test
    void testCEG_BigSales_LowPerformance() {
        // C3 True, C4 False (5 abs), C5 False
        // Intra pe ramura else finala -> 7%
        // 6000 * 7% = 420
        assertEquals(420.0, calculator.calculateBonus(6000, 5, false), 0.01);
    }
}

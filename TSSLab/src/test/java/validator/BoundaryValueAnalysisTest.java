package validator;
import org.example.validator.SalaryBonusCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoundaryValueAnalysisTest {
    SalaryBonusCalculator calculator = new SalaryBonusCalculator();

    // Granite critice identificate:
    // Sales: 999, 1000 (trecere la 5%), 5000 (maxim 5%), 5001 (trecere la 10/7%)
    // absentDays: 2, 3 (limita pentru conditia < 3)

    @Test
    void testBVA_BonusLowerLimit() {
        // 999 -> 0
        assertEquals(0.0, calculator.calculateBonus(999, 0, false), 0.01);
        // 1000 -> 5% (50)
        assertEquals(50.0, calculator.calculateBonus(1000, 0, false), 0.01);
    }

    @Test
    void testBVA_UpperLimitAverage() {
        // 5000 -> 5% (250)
        assertEquals(250.0, calculator.calculateBonus(5000, 0, false), 0.01);
        // 5001 -> 10% (500.1) - intram in zona de sus, presupunand absentDays putine
        assertEquals(500.1, calculator.calculateBonus(5001, 0, false), 0.01);
    }

    @Test
    void testBVA_AbsentDaysLimit() {
        // Vanzari > 5000, Non-Manager
        // 2 absente -> < 3 este True -> 10%
        assertEquals(600.0, calculator.calculateBonus(6000, 2, false), 0.01);
        // 3 absente -> < 3 este False -> 7%
        assertEquals(420.0, calculator.calculateBonus(6000, 3, false), 0.01);
    }
}

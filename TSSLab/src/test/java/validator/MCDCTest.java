package validator;
import org.example.validator.SalaryBonusCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MCDCTest {
    SalaryBonusCalculator calculator = new SalaryBonusCalculator();

    // Decizia tinta: if (absentDays < 3 || isManager)
    // Conditia A: absentDays < 3
    // Conditia B: isManager
    // Expresie: A || B
    //
    // Definim perechi de teste care demonstreaza independenta fiecarei conditii.
    // Pentru OR (||), cazurile critice sunt:
    // 1. False || False -> False (Rezultat 7%)
    // 2. True  || False -> True  (Rezultat 10%) -> Arata ca A influenteaza
    // 3. False || True  -> True  (Rezultat 10%) -> Arata ca B influenteaza

    @Test
    void testMCDC_IndependenceAbsentDays() {
        // Fixam B (isManager) pe False. Variem A.
        // Cazul 1: isManager=False, absentDays=3 (Cond A False) -> Rezultat 7%
        assertEquals(420.0, calculator.calculateBonus(6000, 3, false), 0.01);

        // Cazul 2: Manager=False, Absente=2 (Cond A True) -> Rezultat 10%
        assertEquals(600.0, calculator.calculateBonus(6000, 2, false), 0.01);
    }

    @Test
    void testMCDC_IndependenceManager() {
        // Fixam A (absentDays < 3) pe False (adica punem multe absente). Variem B.
        // Cazul 1: absentDays=4, isManager=False -> Rezultat 7%
        assertEquals(420.0, calculator.calculateBonus(6000, 4, false), 0.01);

        // Cazul 3: absentDays=4, isManager=True -> Rezultat 10%
        assertEquals(600.0, calculator.calculateBonus(6000, 4, true), 0.01);
    }
}

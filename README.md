# 💰 Salary Bonus Calculator - JUnit Testing

![Java](https://img.shields.io/badge/Java-JDK_21-orange)
![JUnit 5](https://img.shields.io/badge/Testing-JUnit_5-green)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![Coverage](https://img.shields.io/badge/Code_Coverage-100%25-brightgreen)

---
* Popescu Paullo Robertto Karloss
* Grupa: 506 IS
* Materie: Testarea Sistemelor Software

## 📄 Documentație Completă
**Acest repository conține proiectul complet de laborator pentru disciplina Testarea Sistemelor Software.**

Pentru o vizualizare detaliată, care include:
* Graful Fluxului de Control (CFG) și Graful Cauză-Efect (CEG) desenate;
* Tabelele de decizie și partiționarea claselor de echivalență;
* Demonstrația matematică pentru MC/DC;
* Analiza detaliată a mutanților.

👉 **Documentația completă în format PDF este [aici](./Documentation/506_Popescu_Paullo_Robertto_Karloss.pdf), în interiorul folderului /Documentation.**

---

## 📌 Descrierea Proiectului
Acest proiect implementează o aplicație Java (`SalaryBonusCalculator`) care calculează bonusul financiar al angajaților pe baza a trei criterii:
1.  **Vânzări (Sales):** Valoarea vânzărilor realizate.
2.  **Absențe (AbsentDays):** Numărul de zile de absență.
3.  **Funcție (IsManager):** Dacă angajatul este sau nu manager.

Obiectivul principal a fost validarea riguroasă a logicii de business folosind tehnici avansate de testare **White Box** și **Black Box**.

### ⚙️ Reguli de Business
* **Sales < 1000:** Bonus 0.
* **1000 <= Sales <= 5000:** Bonus 5%.
* **Sales > 5000:**
    * Dacă (`absentDays < 3` **SAU** `isManager`): Bonus **10%**.
    * Altfel: Bonus **7%**.

---

## 🛠️ Strategii de Testare Utilizate

Proiectul demonstrează aplicarea practică a următoarelor metodologii, asigurând o acoperire a codului de 100%:

### 1. Tehnici Black Box
* **Equivalence Partitioning (EP):** Împărțirea domeniului de intrare în clase valide (vânzări mici, medii, mari) și verificarea fiecărei clase.
* **Boundary Value Analysis (BVA):** Testarea limitelor critice (ex: 999, 1000, 5000, 5001) pentru a detecta erorile de tip "off-by-one".
* **Cause-Effect Graphing (CEG):** Modelarea logicii booleene complexe (AND, OR, NOT) pentru a valida toate scenariile posibile de acordare a bonusului maxim.

### 2. Tehnici White Box & Structurale
* **Code Coverage Analysis:** Utilizarea **JaCoCo** pentru a măsura Instruction Coverage și Branch Coverage.
* **MC/DC (Modified Condition/Decision Coverage):** Analiza nodului decizional critic `if (absentDays < 3 || isManager)` pentru a demonstra independența condițiilor atomice.
* **Control Flow Graph (CFG):** Reprezentarea grafică a tuturor căilor de execuție posibile.

### 3. Mutation Testing
Verificarea calității testelor prin introducerea de defecte artificiale ("mutanți") în cod:
* **Mutant Echivalent:** Modificarea condiției `< 3` cu `<= 2` (nedetectabil, deoarece comportamentul este identic pentru `int`).
* **Mutant Ne-echivalent (Killed):** Introducerea unei erori de calcul (60% bonus) detectată imediat de testele BVA.
* **Mutant Ne-echivalent (Survived):** Identificarea lacunelor în testare prin eliminarea verificării de manager.

---

## 📊 Rezultate și Acoperire
Prin combinarea tuturor suitelor de teste, s-a obținut o acoperire globală perfectă:

| Metrica | Rezultat | Note |
| :--- | :--- | :--- |
| **Instruction Coverage** | **100%** | Toate liniile de cod au fost executate. |
| **Branch Coverage** | **100%** | Toate ramurile decizionale (True/False) au fost parcurse. |

> *Deși suita BVA a obținut individual doar 87% Branch Coverage, combinarea cu testele CEG și EP a asigurat validarea completă a sistemului.*

---

## 🚀 Rularea proiectului

Pentru rularea proiectului este necesară versiunea Java JDK 21 și Maven instalat.

1.  **Clonarea repository-ului:**
    ```bash
    git clone https://github.com/crime-story/SalaryBonusCalculator-JUnit-Testing.git
    ```

2.  **Rularea testelor JUnit folosind linia de comandă:**
    ```bash
    mvn clean test
    ```

3.  **Generarea raportului de coverage (JaCoCo):**
    ```bash
    mvn jacoco:report
    ```
    Raportul va fi disponibil în `target/site/jacoco/index.html`.

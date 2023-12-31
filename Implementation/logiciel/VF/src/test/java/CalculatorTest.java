import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(3, 5);
        assertEquals(8, result);
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(8, 3);
        assertEquals(5, result);
    }

    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(4, 6);
        assertEquals(24, result);
    }

    @Test
    public void testDivide() {
        Calculator calculator = new Calculator();
        int result = calculator.divide(10, 2);
        assertEquals(5, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        Calculator calculator = new Calculator();
        calculator.divide(10, 0);
    }

    @org.junit.jupiter.api.Test
    public void testCalculMoyen() {
        List<String> liste = List.of("10", "2", "33", "42", "5");
        double resultat = Calculator.calculMoyen(liste);

        // Précision de la comparaison des nombres à virgule flottante
        double precision = 0.0001;

        // Vérifier que la moyenne est correcte
        assertEquals(18.4, resultat, precision);
    }


    @org.junit.jupiter.api.Test
    public void testCalculMoyenVide() {
        List<String> liste = List.of();
        double resultat = Calculator.calculMoyen(liste);

        // Précision de la comparaison des nombres à virgule flottante
        double precision = 0.0001;

        // Vérifier que la moyenne est correcte
        assertEquals(0, resultat, precision);
    }

    @org.junit.jupiter.api.Test
    public void testCalculMoyenAvecChaineNonNombre() {
        List<String> liste = List.of("2", "deux", "2", "2", "2");
        double resultat = Calculator.calculMoyen(liste);

        // Précision de la comparaison des nombres à virgule flottante
        double precision = 0.0001;

        // Vérifier que la moyenne est correcte malgré la chaîne non nombre
        assertEquals(1.6, resultat, precision);
    }
}

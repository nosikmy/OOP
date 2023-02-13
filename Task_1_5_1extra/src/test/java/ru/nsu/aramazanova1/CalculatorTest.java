package ru.nsu.aramazanova1;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing calculator.
 */
public class CalculatorTest {
    @Test
    public void exceptionTest() {
        IllegalArgumentException e =
                assertThrows(IllegalArgumentException.class,
                        () -> Calculator.calculate("+ 1"));
        Assertions.assertEquals("Недостаточно операндов", e.getMessage());
        e = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate("logarithm 1"));
        Assertions.assertEquals("Такого оператора не существует", e.getMessage());
        e = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate("sin 0 1"));
        Assertions.assertEquals("Лишний операнд", e.getMessage());
    }

    @Test
    public void complexTest() throws Exception {
        Complex actual = Calculator.calculate("- * pow + sin + 2 3i / log + + 5 6 8i + 6 8i 4 + 5 3i cos 60d");
        Assertions.assertEquals(actual.getRe(), 24196.722565181277);
        Assertions.assertEquals(actual.getIm(), -61512.74081755928);
    }
}

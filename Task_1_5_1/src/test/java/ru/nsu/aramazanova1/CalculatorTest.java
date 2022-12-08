package ru.nsu.aramazanova1;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing calculator.
 */
public class CalculatorTest {
    @Test
    public void exampleTest() {
        Assertions.assertEquals(Calculator.calculate(
                "sin + - 1 2 1"), 0.0);
    }

    @Test
    public void test() {
        Assertions.assertEquals(Calculator.calculate(
                "+ log cos - 1 1 134"), 134.0);
    }

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
}

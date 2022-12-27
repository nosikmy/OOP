package ru.nsu.aramazanova1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Calculator realization.
 */
public class Calculator {

    /**
     * Function for calculation unary expressions.
     *
     * @param a        operand
     * @param operator expression operator
     * @return calculation result
     */
    public static Double forUnaryOperator(Double a, String operator) {
        return switch (operator) {
            case "log" -> Math.log(a);
            case "sqrt" -> Math.sqrt(a);
            case "sin" -> Math.sin(a);
            case "cos" -> Math.cos(a);
            default -> throw new IllegalArgumentException("Такого оператора не существует");
        };
    }

    /**
     * Function for calculation binary expressions.
     *
     * @param a        first operand
     * @param b        second operand
     * @param operator expression operator
     * @return calculation result
     */
    public static Double forBinaryOperator(Double a, Double b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "pow" -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Такого оператора не существует");
        };
    }

    /**
     * Function to calculate prefix expression.
     *
     * @param expression prefix expression
     * @return calculation result
     */
    public static Double calculate(String expression) {
        List<String> partsOfExpression = new ArrayList<>(List.of(expression.split(" ")));
        Collections.reverse(partsOfExpression);
        Stack<Double> operands = new Stack<>();

        for (String part : partsOfExpression) {
            if (Character.isDigit(part.charAt(0))) {
                operands.push(Double.valueOf(part));
            } else {
                Double a;
                Double b;
                switch (part) {
                    case "+", "-", "*", "/", "pow" -> {
                        if (operands.size() < 2) {
                            throw new IllegalArgumentException("Недостаточно операндов");
                        }
                        a = operands.pop();
                        b = operands.pop();
                        operands.push(forBinaryOperator(a, b, part));
                    }
                    case "log", "sqrt", "sin", "cos" -> {
                        a = operands.pop();
                        operands.push(forUnaryOperator(a, part));
                    }
                    default -> throw new IllegalArgumentException("Такого оператора не существует");
                }
            }

        }
        if (operands.size() > 1) {
            throw new IllegalArgumentException("Лишний операнд");
        }
        return operands.peek();
    }
}

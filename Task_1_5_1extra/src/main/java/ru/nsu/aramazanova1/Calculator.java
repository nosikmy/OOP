package ru.nsu.aramazanova1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Calculator realization.
 */
public class Calculator {

    public static String removeLastChar(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * Function for calculation unary expressions.
     *
     * @param a        operand
     * @param operator expression operator
     * @return calculation result
     */
    public static Complex forUnaryOperator(Complex a, String operator) {
        return switch (operator) {
            case "log" -> a.log();
            case "sqrt" -> a.sqrt();
            case "sin" -> a.sin();
            case "cos" -> a.cos();
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
    public static Complex forBinaryOperator(Complex a, Complex b, String operator) throws Exception {
        return switch (operator) {
            case "+" -> a.sum(b);
            case "-" -> a.dif(b);
            case "*" -> a.mult(b);
            case "/" -> a.div(b);
            case "pow" -> a.pow(b);
            default -> throw new IllegalArgumentException("Такого оператора не существует");
        };
    }

    /**
     * Function to calculate prefix expression.
     *
     * @param expression prefix expression
     * @return calculation result
     */
    public static Complex calculate(String expression) throws Exception {
        List<String> partsOfExpression = new ArrayList<>(List.of(expression.split(" ")));
        Collections.reverse(partsOfExpression);
        Stack<Complex> operands = new Stack<>();

        for (String part : partsOfExpression) {
            if (Character.isDigit(part.charAt(0))) {
                Complex complex = new Complex();
                if(part.charAt(part.length() - 1) == 'i'){
                    part = removeLastChar(part);
                    complex.setIm(Double.valueOf(part));
                } else if (part.charAt(part.length() - 1) == 'd') {
                    part = removeLastChar(part);
                    complex.setRe(Double.parseDouble(part) * Math.PI / 180);
                } else {
                    complex.setRe(Double.valueOf(part));
                }
                operands.push(complex);
            } else {
                Complex a;
                Complex b;
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

package ru.nsu.aramazanova1;

import java.util.Scanner;

/**
 * Main class.
 */
public class Main {
    /**
     * The function scans the expression and outputs the answer to the standard output stream.
     *
     * @param args args
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String expression;
        System.out.println("Press q to quit");
        while (true){
            expression = scanner.nextLine();
            if(expression.equals("q")){
                break;
            }
            Double result = Calculator.calculate(expression);
            System.out.println(result);
        }
        scanner.close();
    }
}

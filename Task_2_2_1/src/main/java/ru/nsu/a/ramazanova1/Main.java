package ru.nsu.a.ramazanova1;

/**
 * Main class.
 */
public class Main {
    /**
     * Main class to start the program
     *
     * @param args args
     */
    public static void main(String[] args) {
        Cashier cashier = new Cashier();
        (new Thread(cashier)).start();
    }
}

package ru.nsu.a.ramazanova1;

public class Main {
    public static void main(String[] args) {
        System.out.println(Math.ceil(0.0667));
        Cashier cashier = new Cashier();
        (new Thread(cashier)).start();
    }
}

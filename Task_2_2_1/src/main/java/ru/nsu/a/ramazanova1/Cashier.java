package ru.nsu.a.ramazanova1;

import java.util.Scanner;

/**
 * Class for managing a pizzeria and taking orders.
 */
public class Cashier implements Runnable {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        CashRegister cashRegister = new CashRegister("info.json");
        cashRegister.openPizzeria();
        System.out.println("Pizzeria is opened");
        while (true) {
            if (scanner.hasNextInt()) {
                int size = scanner.nextInt();
                if (size == -1) {
                    try {
                        cashRegister.closePizzeria();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Pizzeria is closed");
                    return;
                }
                for (int i = 0; i < size; i++) {
                    try {
                        Order newOrder = new Order();
                        cashRegister.addOrder(newOrder);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

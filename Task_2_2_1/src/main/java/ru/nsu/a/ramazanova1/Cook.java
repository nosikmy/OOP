package ru.nsu.a.ramazanova1;

import java.util.concurrent.TimeUnit;

public class Cook implements Runnable {
    private final String name;
    private final int experience;

    public boolean isWorking() {
        return isWorking;
    }

    private boolean isWorking = false;

    public Cook(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Order order = CashRegister.takeOrder();
                isWorking = true;
                order.setStatus(Order.Status.COOKING);
                System.out.println(order + " by " + name);
                TimeUnit.SECONDS.sleep(10 - experience);
                order.setStatus(Order.Status.DONE);
                isWorking = false;
                System.out.println(order + " by " + name);
                CashRegister.addToStock(order);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
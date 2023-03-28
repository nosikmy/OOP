package ru.nsu.a.ramazanova1;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class for deliveryman task.
 */
public class Deliveryman implements Runnable {
    private final String name;

    private final int bagCapacity;
    private boolean isWorking = false;

    public int getBagCapacity() {
        return bagCapacity;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public Deliveryman(String name, int bagCapacity) {
        this.name = name;
        this.bagCapacity = bagCapacity;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                List<Order> orders = CashRegister.takeFromStock(bagCapacity);
                isWorking = true;
                System.out.println(orders.size() + " orders took by " + name);
                for (Order order : orders) {
                    order.setStatus(Order.Status.DELIVERING);
                    System.out.println(order + " by " + name);
                    TimeUnit.SECONDS.sleep((int) (Math.random() * 10 + 1));
                    order.setStatus(Order.Status.DELIVERED);
                    System.out.println(order + " by " + name);
                }
                isWorking = false;
                System.out.println(name + " is going back");
                TimeUnit.SECONDS.sleep((int) (Math.random() * 10 + 1));
                System.out.println(name + " is ready to deliver");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

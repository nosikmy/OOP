package ru.nsu.a.ramazanova1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Math.ceil;
import static java.lang.Math.min;

public class CashRegister {

    private static volatile BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static volatile BlockingQueue<Order> stockQueue;
    private final List<Cook> cooks;
    private final List<Deliveryman> deliverymen;
    private final ExecutorService cooksPool;
    private final ExecutorService deliverymenPool;

    static int totalBagCapacity = 0;

    public CashRegister(String file) {
        JsonData jsonData = new JsonData(file);
        stockQueue = new LinkedBlockingQueue<>(jsonData.getStockCapacity());
        this.cooks = jsonData.getCooks();
        this.deliverymen = jsonData.getDeliverymen();
        totalBagCapacity = deliverymen.stream().mapToInt(Deliveryman::getBagCapacity).sum();
        cooksPool = Executors.newFixedThreadPool(cooks.size());
        deliverymenPool = Executors.newFixedThreadPool(deliverymen.size());
    }

    public void openPizzeria() {

        cooks.forEach(cooksPool::submit);
        deliverymen.forEach(deliverymenPool::submit);
    }

    public void addOrder(Order order) throws InterruptedException {
        orderQueue.put(order);
        order.setStatus(Order.Status.ORDERED);
    }

    public static Order takeOrder() throws InterruptedException {
        return orderQueue.take();
    }

    public static void addToStock(Order order) throws InterruptedException {
        stockQueue.put(order);
        order.setStatus(Order.Status.IN_STOCK);
        System.out.println(order);
    }

    public static List<Order> takeFromStock(int bagCapacity) throws InterruptedException {
        List<Order> orders = new ArrayList<>();
        int count = (int) min(ceil((double) (stockQueue.size()/ totalBagCapacity) * bagCapacity) + 1, bagCapacity);
        for (int i = 0; i < count; i++) {
            orders.add(stockQueue.take());
        }
        return orders;
    }

    public void closePizzeria() throws InterruptedException {
        while (true){
            if(orderQueue.isEmpty() && cooks.stream().noneMatch(Cook::isWorking)){
                cooksPool.shutdownNow();
                System.out.println("Cooks is going home");
                break;
            }
        }
        while (true){
            if(stockQueue.isEmpty() && deliverymen.stream().noneMatch(Deliveryman::isWorking)){
                deliverymenPool.shutdownNow();
                System.out.println("Deliverymen is going home");
                break;
            }
        }
    }
}

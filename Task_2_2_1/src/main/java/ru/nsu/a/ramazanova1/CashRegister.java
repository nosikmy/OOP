package ru.nsu.a.ramazanova1;

import static java.lang.Math.ceil;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class for distributing orders between workers and starting their work.
 */
public class CashRegister {

    private static volatile BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static volatile BlockingQueue<Order> stockQueue;
    private final List<Cook> cooks;
    private final List<Deliveryman> deliverymen;
    private final ExecutorService cooksPool;
    private final ExecutorService deliverymenPool;
    static int totalBagCapacity = 0;

    /**
     * Constructor for pizzeria. Get data from json file and fills fields of this class.
     *
     * @param file Json file name
     */
    public CashRegister(String file) {
        JsonData jsonData = new JsonData(file);
        stockQueue = new LinkedBlockingQueue<>(jsonData.getStockCapacity());
        this.cooks = jsonData.getCooks();
        this.deliverymen = jsonData.getDeliverymen();
        totalBagCapacity = deliverymen.stream().mapToInt(Deliveryman::getBagCapacity).sum();
        cooksPool = Executors.newFixedThreadPool(cooks.size());
        deliverymenPool = Executors.newFixedThreadPool(deliverymen.size());
    }

    /**
     * Method for getting started cooks and deliverymen.
     */
    public void openPizzeria() {

        cooks.forEach(cooksPool::submit);
        deliverymen.forEach(deliverymenPool::submit);
    }

    /**
     * Puts new order to the queue.
     *
     * @param order new order
     * @throws InterruptedException if interrupted while waiting
     */
    public void addOrder(Order order) throws InterruptedException {
        orderQueue.put(order);
        order.setStatus(Order.Status.ORDERED);
    }

    /**
     * Takes order from queue for cooks.
     *
     * @return order for cooking
     * @throws InterruptedException if interrupted while waiting
     */
    public static Order takeOrder() throws InterruptedException {
        return orderQueue.take();
    }

    /**
     * Add cooked order to stock.
     *
     * @param order cooked order
     * @throws InterruptedException if interrupted while waiting
     */
    public static void addToStock(Order order) throws InterruptedException {
        stockQueue.put(order);
        order.setStatus(Order.Status.IN_STOCK);
        System.out.println(order);
    }

    /**
     * Takes list of orders from stock for deliveryman.
     *
     * @param bagCapacity Capacity of deliveryman's bag
     * @return list of orders for delivery
     * @throws InterruptedException if interrupted while waiting
     */
    public static List<Order> takeFromStock(int bagCapacity) throws InterruptedException {
        List<Order> orders = new ArrayList<>();
        int count = (int) min(ceil(
                ((double) stockQueue.size() / totalBagCapacity) * bagCapacity + 1), bagCapacity);
        for (int i = 0; i < count; i++) {
            orders.add(stockQueue.take());
        }
        return orders;
    }

    /**
     * Closing pizzeria. Stops the work of cooks and deliverymen.
     *
     * @throws InterruptedException if interrupted while waiting
     */
    public void closePizzeria() throws InterruptedException {
        while (true) {
            if (orderQueue.isEmpty() && cooks.stream().noneMatch(Cook::isWorking)) {
                cooksPool.shutdown();
                System.out.println("Cooks is going home");
                break;
            }
        }
        while (true) {
            if (stockQueue.isEmpty() && deliverymen.stream().noneMatch(Deliveryman::isWorking)) {
                deliverymenPool.shutdown();
                System.out.println("Deliverymen is going home");
                break;
            }
        }
    }
}

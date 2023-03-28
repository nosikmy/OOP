package ru.nsu.a.ramazanova1;

public class Order {
    private int id;
    static int number = 0;
    public enum Status {
        ABSENT,
        ORDERED,
        COOKING,
        DONE,
        IN_STOCK,
        DELIVERING,
        DELIVERED
    }
    Status status;
    public Order() {
        this.id = number;
        number++;
        this.status = Status.ABSENT;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}

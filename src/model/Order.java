package model;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Order {
    private LocalDateTime orderTimestamp = LocalDateTime.now();
    private double totalCost;
    private HashSet<Items> itemMap = new HashSet<>();

    public Order() {}

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(LocalDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public HashSet<Items> getItemMap() {
        return itemMap;
    }

    public void setItemMap(HashSet<Items> itemMap) {
        this.itemMap = itemMap;
    }

    @Override
    public String toString() {
        return "Order {" +
                "orderDate: " + orderTimestamp +
                ", totalPrice: " + totalCost +
                ", items: " + itemMap +
                '}';
    }
}

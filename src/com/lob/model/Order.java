package com.lob.model;

public class Order {

    private long orderId;
    private OrderSide side;
    private OrderType type;
    private double price;
    private int quantity;
    private long timestamp;

    // Constructor
    public Order(long orderId, OrderSide side, OrderType type, double price, int quantity){
        this.orderId = orderId;
        this.side = side;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = System.nanoTime();
    }

    // Getters
    public long getOrderId(){
        return orderId;
    }

    public OrderSide getSide(){
        return side;
    }

    public OrderType getType(){
        return type;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    //Method for reduce quantity
    public void reduceQty(int tradedQty){
        if(tradedQty <= 0){
            throw new IllegalArgumentException("Traded quantity must be positive");
        }
        if(tradedQty > this.quantity){
            throw new IllegalArgumentException("Traded quantity exceeds available quantity");
        }

        this.quantity -= tradedQty;
    }
    

    @Override
    public String toString(){
        return "Order{" +
                "id=" + orderId +
                ", side=" + side +
                ", type=" + type +
                ", price=" + price +
                ", qty=" + quantity +
                ", time=" + timestamp +
                '}';
    }
}

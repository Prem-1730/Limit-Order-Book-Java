package com.lob.model;

public class Trade {

    // private long tradeId;
    private long buyOrderId;
    private long sellOrderId;
    private double price;
    private int quantity;
    private long timestamp;

    // Constructor
    public Trade( long buyOrderId, long sellOrderId, double price, int quantity) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = System.nanoTime(); // execution time
    }


    public long getBuyOrderId() {
        return buyOrderId;
    }

    public long getSellOrderId() {
        return sellOrderId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Trade{" +
                // "tradeId=" + tradeId +",
                " buyOrderId=" + buyOrderId +
                ", sellOrderId=" + sellOrderId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                '}';
    }
}

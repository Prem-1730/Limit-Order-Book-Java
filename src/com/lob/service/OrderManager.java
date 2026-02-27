package com.lob.service;

import com.lob.engine.OrderBook;
import com.lob.model.Order;
import com.lob.model.OrderSide;
import com.lob.model.OrderType;

public class OrderManager {
    
    private OrderBook orderbook;
    private long orderIdCounter = 1;

    public OrderManager(OrderBook orderBook){
        this.orderbook = orderBook;
    }

    public void placeLimitOrder(OrderSide side, double price, int quantity){
        Order order = new Order(orderIdCounter++, side, OrderType.LIMIT,price,quantity);
        orderbook.addLimitOrder(order);
    }

    public void placeMarketOrder(OrderSide side, int quantity){
        Order order = new Order(orderIdCounter++, side, OrderType.MARKET, 0.0, quantity);
        orderbook.processMarketOrder(order);
    }

    public void cancelOrder(long orderId){
        orderbook.cancelOrder(orderId);
    }
}

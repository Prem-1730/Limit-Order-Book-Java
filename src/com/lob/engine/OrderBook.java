package com.lob.engine;

import com.lob.model.Order;
import com.lob.model.OrderSide;
import com.lob.model.Trade;
import com.lob.util.TradeLogger;
import java.util.*;

public class OrderBook {
    
    private TreeMap<Double,LinkedList<Order>> bids;
    private TreeMap<Double,LinkedList<Order>> asks;
    private HashMap<Long, Order> orderMap;

    // Constructor
    public OrderBook(){
        bids = new TreeMap<>(Collections.reverseOrder()); //Descending order
        asks = new TreeMap<>(); //Ascending order
        orderMap = new HashMap<>();
    }

    public void addLimitOrder(Order order){
        if(order.getSide()== OrderSide.BUY){
            matchBuy(order);
        }
        else{
            matchSell(order);
        }

        if(order.getQuantity()>0){
            addToBook(order);
        }
    }

    public void addToBook(Order order){

        TreeMap<Double , LinkedList<Order>> sideMap = (order.getSide() == OrderSide.BUY) ? bids : asks ;
        double price = order.getPrice();
        LinkedList<Order> queue = sideMap.get(price);
        
        if( queue == null){
            queue = new LinkedList<>();
            sideMap.put(price,queue);
        }

        queue.add(order);

        orderMap.put(order.getOrderId(),order);
    }

    //Match buy method
    private void matchBuy(Order buyOrder){

        while(buyOrder.getQuantity() > 0 && !asks.isEmpty()){
            double bestAskPrice = asks.firstKey();

            if(bestAskPrice > buyOrder.getPrice()){
                break;
            }
            
            LinkedList<Order> queue = asks.get(bestAskPrice);
            Order sellOrder = queue.peek();

            int tradedQty = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

            buyOrder.reduceQty(tradedQty);
            sellOrder.reduceQty(tradedQty);

            Trade trade = new Trade(
                buyOrder.getOrderId(),
                sellOrder.getOrderId(),
                bestAskPrice,
                tradedQty
            );

            TradeLogger.logTrade(trade);

            if(sellOrder.getQuantity() == 0){
                queue.poll();
                orderMap.remove(sellOrder.getOrderId());
            }

            if(queue.isEmpty()){
                asks.remove(bestAskPrice);
            }
        }
    }

    //Match Sell method
    private void matchSell(Order sellOrder){

        while(sellOrder.getQuantity() > 0 && !bids.isEmpty()){
            double bestBidPrice = bids.firstKey();

            if(bestBidPrice < sellOrder.getPrice()){
                break;
            }
            
            LinkedList<Order> queue = bids.get(bestBidPrice);
            if (queue == null || queue.isEmpty()) {
                return;   // nothing to match
            }
            Order buyOrder = queue.peek();

            int tradedQty = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

            sellOrder.reduceQty(tradedQty);
            buyOrder.reduceQty(tradedQty);

             Trade trade = new Trade(
                buyOrder.getOrderId(),
                sellOrder.getOrderId(),
                bestBidPrice,
                tradedQty
            );

            TradeLogger.logTrade(trade);

            if(buyOrder.getQuantity() == 0){
                queue.poll();
                orderMap.remove(buyOrder.getOrderId());
            }

            if(queue.isEmpty()){
                bids.remove(bestBidPrice);
            }
        }
    }

    public Double getBestBidPrice(){
        return bids.isEmpty() ? null : bids.firstKey();
    }

    public Double getBestAskPrice(){
        return asks.isEmpty() ? null : asks.firstKey();
    }

    public Order getOrder(long orderId){
        return orderMap.get(orderId);
    }

    public void processMarketOrder(Order order){

        if(order.getSide() == OrderSide.BUY){
            matchMarketBuy(order);
        }
        else{
            matchMarketSell(order);
        }
    }

    private void matchMarketBuy(Order buyOrder){

        while(buyOrder.getQuantity() > 0 && !asks.isEmpty()){
            double bestAskPrice = asks.firstKey();

            LinkedList<Order> queue = asks.get(bestAskPrice);
            Order sellOrder = queue.peek();

            int tradedQty = Math.min(buyOrder.getQuantity(),sellOrder.getQuantity());

            buyOrder.reduceQty(tradedQty);
            sellOrder.reduceQty(tradedQty);

             Trade trade = new Trade(
                buyOrder.getOrderId(),
                sellOrder.getOrderId(),
                bestAskPrice,
                tradedQty
            );

            TradeLogger.logTrade(trade);

            if(sellOrder.getQuantity()==0){
                queue.poll();
                orderMap.remove(sellOrder.getOrderId());
            }

            if(queue.isEmpty()){
                asks.remove(bestAskPrice);
            }
        }
    }

    private void matchMarketSell(Order sellOrder){

        while(sellOrder.getQuantity() > 0 && !bids.isEmpty()){
            double bestBidPrice = bids.firstKey();
            LinkedList<Order> queue = bids.get(bestBidPrice);
            Order buyOrder = queue.peek();

            int tradedQty = Math.min(buyOrder.getQuantity(),sellOrder.getQuantity());

            buyOrder.reduceQty(tradedQty);
            sellOrder.reduceQty(tradedQty);

            // System.out.println(
            //     "TRADE price=" + bestBidPrice +
            //     " qty=" + tradedQty +
            //     " buy=" + buyOrder.getOrderId() +
            //     " sell=" + sellOrder.getOrderId()
            // );

            if(buyOrder.getQuantity()==0){
                queue.poll();
                orderMap.remove(buyOrder.getOrderId());
            }

            if(queue.isEmpty()){
                bids.remove(bestBidPrice);
            }
        }
    }

    public void cancelOrder(long orderId){
        
        Order order = orderMap.get(orderId);
        if(order == null) {
            return;
        }

        TreeMap<Double,LinkedList<Order>> sideMap = (order.getSide() == OrderSide.BUY) ? bids: asks;
        double price = order.getPrice();

        LinkedList<Order> queue = sideMap.get(price);

        if(queue!=null){
            queue.remove(order);
            if(queue.isEmpty()){
                sideMap.remove(price);
            }
        }
        
        orderMap.remove(orderId);

        System.out.println("Cancelled orderId: " + orderId);
    }

    
}


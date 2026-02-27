package com.lob;

import com.lob.engine.OrderBook;
import com.lob.model.OrderSide;
import com.lob.model.OrderType;
import com.lob.service.OrderManager;
import com.lob.simulator.OrderGenerator;

public class Main{
    public static void main(String[]args){
        OrderBook orderbook = new OrderBook();
        OrderManager manager = new OrderManager(orderbook);
        OrderGenerator generator = new OrderGenerator();

        int totalOrders = 1_000_000;

        long startTimer = System.nanoTime();

        for(int i=0;i<totalOrders;i++){

            OrderSide side = generator.randomSide();
            OrderType type = generator.randomType();
            int qty = generator.randomQuantity();

            if(type == OrderType.LIMIT){
                double price = generator.randomPrice();
                manager.placeLimitOrder(side, price, qty);
            }
            else{
                manager.placeMarketOrder(side, qty);
            }
        }

        long endTimer = System.nanoTime();

        long totalTimeNs = endTimer - startTimer;
        double totalTimeS = totalTimeNs / 1_000_000_000.0;

        double throughput = totalOrders / totalTimeS;
        double avgLatencyNs = totalTimeNs / (double) totalOrders;

        System.out.println("\n===== PERFORMANCE =====");
        System.out.println("Total Orders: " + totalOrders);
        System.out.println("Total Time: " + totalTimeS + " sec");
        System.out.println("Throughput: " + throughput + " orders/sec");
        System.out.println("Avg Latency: " + avgLatencyNs + " ns/order");

    }
}

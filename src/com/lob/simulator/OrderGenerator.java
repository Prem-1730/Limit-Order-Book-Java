package com.lob.simulator;

import com.lob.model.OrderSide;
import com.lob.model.OrderType;
import java.util.Random;

public class OrderGenerator {
    
    private Random random = new Random();
    private double basePrice = 100.0;

    public OrderSide randomSide(){
        return random.nextBoolean() ? OrderSide.BUY : OrderSide.SELL;
    }

    public OrderType randomType(){
        return random.nextBoolean() ? OrderType.LIMIT : OrderType.MARKET;
    }

    public double randomPrice(){
        return basePrice + (random.nextInt(11) - 5);
    }

    public int randomQuantity(){
        return random.nextInt(100) + 1;
    }
}

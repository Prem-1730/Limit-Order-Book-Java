package com.lob.util;

import com.lob.model.Trade;
import java.util.ArrayList;
import java.util.List;

public class TradeLogger {
    //Storage of all trades
    private static List<Trade> trades = new ArrayList<>();

    //Main logging method
    public static void logTrade(Trade trade){
        
        trades.add(trade);

        // System.out.println("TRADE: price=" + trade.getPrice() +
        //         " qty=" + trade.getQuantity() +
        //         " buy=" + trade.getBuyOrderId() +
        //         " sell=" + trade.getSellOrderId()
        // );
    }
    
    public static List<Trade> getTrades(){
        return trades;
    }
}

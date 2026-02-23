package service;

import model.Trade;
import model.TradeType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PortfolioService {

    // AccountId -> (Symbol -> Quantity)
    private final Map<Long, Map<String, Integer>> portfolio =
            new ConcurrentHashMap<>();

    public void updatePortfolio(Trade trade) {

        portfolio.putIfAbsent(trade.getAccountId(),
                new ConcurrentHashMap<>());

        Map<String, Integer> positions =
                portfolio.get(trade.getAccountId());

        positions.compute(trade.getSymbol(), (symbol, currentQty) -> {

            if (currentQty == null) currentQty = 0;

            if (trade.getType() == TradeType.BUY) {
                return currentQty + trade.getQuantity();
            }

            if (trade.getType() == TradeType.SELL) {

                if (currentQty < trade.getQuantity()) {
                    throw new IllegalArgumentException(
                            "Insufficient shares for SELL. Available: " + currentQty
                    );
                }

                return currentQty - trade.getQuantity();
            }

            return currentQty;
        });
    }

    public void printPortfolio() {
        System.out.println("\nFinal Portfolio State:");

        portfolio.forEach((account, positions) -> {
            System.out.println("Account: " + account);
            positions.forEach((symbol, qty) ->
                    System.out.println("  " + symbol + " -> " + qty)
            );
        });
    }
}
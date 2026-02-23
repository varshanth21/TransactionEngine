package service;

import model.Trade;

public class TradeValidator {

    public static void validateBasic(Trade trade) {

        if (trade.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (trade.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        if (trade.getSymbol() == null || trade.getSymbol().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }

        if (trade.getType() == null) {
            throw new IllegalArgumentException("Trade type cannot be null");
        }
    }
}
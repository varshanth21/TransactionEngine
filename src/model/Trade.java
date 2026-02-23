package model;

public class Trade {

    private long tradeId;
    private long accountId;
    private String symbol;
    private int quantity;
    private double price;
    private TradeType type;

    public Trade(long tradeId,
                 long accountId,
                 String symbol,
                 int quantity,
                 double price,
                 TradeType type) {

        this.tradeId = tradeId;
        this.accountId = accountId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public long getTradeId() {
        return tradeId;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public TradeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", accountId=" + accountId +
                ", symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
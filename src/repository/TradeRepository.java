package repository;

import model.Trade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TradeRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/trading_engine";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin123"; // change if needed

    public void saveTrade(Trade trade) {

        String sql = "INSERT INTO trades(account_id, symbol, quantity, price, type) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, trade.getAccountId());
            stmt.setString(2, trade.getSymbol());
            stmt.setInt(3, trade.getQuantity());
            stmt.setDouble(4, trade.getPrice());
            stmt.setString(5, trade.getType().name());

            stmt.executeUpdate();

            System.out.println("Inserted trade into DB");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
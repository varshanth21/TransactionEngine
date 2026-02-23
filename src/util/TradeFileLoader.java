package util;

import model.Trade;
import model.TradeType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradeFileLoader {

    public static List<Trade> loadTrades(String filePath) {

        List<Trade> trades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {

                // Skip header
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");

                long tradeId = Long.parseLong(parts[0].trim());
                long accountId = Long.parseLong(parts[1].trim());
                String symbol = parts[2].trim();
                int quantity = Integer.parseInt(parts[3].trim());
                double price = Double.parseDouble(parts[4].trim());
                TradeType type = TradeType.valueOf(parts[5].trim().toUpperCase());

                Trade trade = new Trade(
                        tradeId,
                        accountId,
                        symbol,
                        quantity,
                        price,
                        type
                );

                trades.add(trade);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trades;
    }
}
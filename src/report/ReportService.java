package report;

import model.Trade;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    public static void generateReports(List<Trade> trades) {

        System.out.println("\n================ REPORTS ================\n");

        // 1️⃣ Total Quantity Per Symbol
        Map<String, Integer> quantityPerSymbol =
                trades.stream()
                        .collect(Collectors.groupingBy(
                                Trade::getSymbol,
                                Collectors.summingInt(Trade::getQuantity)
                        ));

        System.out.println("Total Quantity Per Symbol:");
        quantityPerSymbol.forEach((symbol, qty) ->
                System.out.println(symbol + " -> " + qty)
        );


        // 2️⃣ Total Trade Value Per Account
        Map<Long, Double> tradeValuePerAccount =
                trades.stream()
                        .collect(Collectors.groupingBy(
                                Trade::getAccountId,
                                Collectors.summingDouble(
                                        t -> t.getQuantity() * t.getPrice()
                                )
                        ));

        System.out.println("\nTotal Trade Value Per Account:");
        tradeValuePerAccount.forEach((account, value) ->
                System.out.println("Account " + account + " -> " + value)
        );


        // 3️⃣ Total BUY vs SELL count
        Map<String, Long> buySellCount =
                trades.stream()
                        .collect(Collectors.groupingBy(
                                t -> t.getType().name(),
                                Collectors.counting()
                        ));

        System.out.println("\nBUY vs SELL Count:");
        buySellCount.forEach((type, count) ->
                System.out.println(type + " -> " + count)
        );


        // 4️⃣ Most Traded Symbol (by quantity)
        String topSymbol =
                quantityPerSymbol.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("None");

        System.out.println("\nMost Traded Symbol: " + topSymbol);

        System.out.println("\n=========================================");
    }
}
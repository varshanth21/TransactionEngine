package main;

import model.Trade;
import repository.TradeRepository;
import service.PortfolioService;
import service.TradeValidator;
import util.TradeFileLoader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import report.ReportService;
public class Main {

    public static void main(String[] args) {

        String filePath = "trades.csv";

        List<Trade> trades = TradeFileLoader.loadTrades(filePath);

        PortfolioService portfolioService = new PortfolioService();
        TradeRepository tradeRepository = new TradeRepository();

        // Create thread pool with 5 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (Trade trade : trades) {

            executor.submit(() -> {
                try {
                    TradeValidator.validateBasic(trade);
                    portfolioService.updatePortfolio(trade);
                    tradeRepository.saveTrade(trade);

                    System.out.println("Processed: " + trade);

                } catch (Exception e) {
                    System.out.println("Rejected: " + trade);
                    System.out.println("Reason: " + e.getMessage());
                }
            });
        }

        // Shutdown executor properly
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        portfolioService.printPortfolio();
        ReportService.generateReports(trades);
    }
}
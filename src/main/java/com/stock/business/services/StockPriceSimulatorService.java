package com.stock.business.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.stock.domain.entities.StockPrice;
import com.stock.infrastructure.dtos.StockPriceResponseDTO;
import com.stock.infrastructure.repositories.StockPriceRepository;
import com.stock.infrastructure.websocket.StockWebSocket;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class StockPriceSimulatorService {

    @Inject
    private StockPriceRepository stockPriceRepository;
    @Inject
    private StockWebSocket stockWebSocket;

    @Scheduled(fixedDelay = "2s", initialDelay = "1s")
    public void updatePrices(){
        List<StockPrice> stockPrices = stockPriceRepository.findAll();
        for (StockPrice stockPrice:stockPrices){
            BigDecimal newPrice = simulatePriceChange(stockPrice.getPrice());
            stockPrice.setPrice(newPrice);
            stockPriceRepository.update(stockPrice);

            boolean alert = stockPrice.getPrice().compareTo(stockPrice.getThreshold()) <= 0;

            StockPriceResponseDTO dto = new StockPriceResponseDTO(
                    stockPrice.getSymbol(),
                    stockPrice.getPrice(),
                    stockPrice.getThreshold(),
                    alert
            );

            stockWebSocket.sendStockUpdate(dto);
        }

    }

    private BigDecimal simulatePriceChange(BigDecimal price){
        double changePercentage = (Math.random() * 2 - 1) * 0.01;
        BigDecimal multiplier = BigDecimal.valueOf(1 + changePercentage);
        return price.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }
}

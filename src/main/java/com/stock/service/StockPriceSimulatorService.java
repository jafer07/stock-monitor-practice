package com.stock.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.stock.entities.StockPrice;
import com.stock.repositories.StockPriceRepository;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class StockPriceSimulatorService {

    @Inject
    private StockPriceRepository stockPriceRepository;

    @Scheduled(fixedDelay = "2s", initialDelay = "1s")
    public void updatePrices(){
        List<StockPrice> stockPrices = stockPriceRepository.findAll();
        for (StockPrice stockPrice:stockPrices){
            BigDecimal newPrice = simulatePriceChange(stockPrice.getPrice());
            stockPrice.setPrice(newPrice);
            stockPriceRepository.update(stockPrice);
        }

    }

    private BigDecimal simulatePriceChange(BigDecimal price){
        double changePercentage = (Math.random() * 2 - 1) * 0.01;
        BigDecimal multiplier = BigDecimal.valueOf(1 + changePercentage);
        return price.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }
}

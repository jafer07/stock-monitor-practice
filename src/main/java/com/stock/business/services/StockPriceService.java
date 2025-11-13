package com.stock.business.services;

import java.util.Optional;

import com.stock.domain.entities.StockPrice;
import com.stock.infrastructure.dtos.CreateStockPriceDTO;
import com.stock.infrastructure.dtos.StockPriceResponseDTO;
import com.stock.infrastructure.dtos.UpdateStockPriceDTO;
import com.stock.infrastructure.repositories.StockPriceRepository;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
public class StockPriceService {

    private final StockPriceRepository stockPriceRepository;

    public StockPriceService(StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }

    @Transactional
    public Optional<StockPriceResponseDTO> createStockPrice(CreateStockPriceDTO createStockPriceDTO) {

        final String symbol = createStockPriceDTO.getSymbol().trim().toUpperCase();

        // Check if stock with the same symbol already exists and return empty
        // if it does to avoid duplicates
        if (stockPriceRepository.existsById(symbol)) {
            return Optional.empty();
        }

        StockPrice stockPrice = new StockPrice(
                symbol,
                createStockPriceDTO.getInitialPrice(),
                createStockPriceDTO.getThreshold());

        StockPrice saved = stockPriceRepository.save(stockPrice);

        boolean alert = saved.getPrice() != null
                && saved.getThreshold() != null
                && saved.getPrice().compareTo(saved.getThreshold()) <= 0;

        return Optional.of(new StockPriceResponseDTO(
                saved.getSymbol(),
                saved.getPrice(),
                saved.getThreshold(),
                alert));
    }

    public Optional<StockPriceResponseDTO> getStock(String symbol) {

        return stockPriceRepository.findById(symbol.trim().toUpperCase())
                .map(stockPrice -> {
                    boolean alert = stockPrice.getPrice() != null
                            && stockPrice.getThreshold() != null
                            && stockPrice.getPrice().compareTo(stockPrice.getThreshold()) <= 0;

                    return new StockPriceResponseDTO(
                            stockPrice.getSymbol(),
                            stockPrice.getPrice(),
                            stockPrice.getThreshold(),
                            alert);
                });
    }

    @Transactional
    public Optional<StockPriceResponseDTO> updateStock(UpdateStockPriceDTO updateStockPriceDTO) {

        return stockPriceRepository.findById(updateStockPriceDTO.getSymbol().trim().toUpperCase())
                .map(stockPrice -> {
                    stockPrice.setPrice(updateStockPriceDTO.getPrice());
                    stockPrice.setThreshold(updateStockPriceDTO.getThreshold());

                    StockPrice updated = stockPriceRepository.update(stockPrice);

                    boolean alert = updated.getPrice() != null
                            && updated.getThreshold() != null
                            && updated.getPrice().compareTo(updated.getThreshold()) <= 0;

                    return new StockPriceResponseDTO(
                            updated.getSymbol(),
                            updated.getPrice(),
                            updated.getThreshold(),
                            alert);
                });
    }

    @Transactional
    public boolean deleteStock(String symbol) {
        String trimmedSymbol = symbol.trim().toUpperCase();
        if (stockPriceRepository.existsById(trimmedSymbol)) {
            stockPriceRepository.deleteById(trimmedSymbol);
            return true;
        } else {
            return false;
        }
    }
}

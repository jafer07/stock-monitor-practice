package com.stock.dbinit;

import com.stock.infrastructure.repositories.StockPriceRepository;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class DbInitLogger {

    private final StockPriceRepository repository;

    public DbInitLogger(StockPriceRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    void init() {
        long count = repository.count();
        System.out.println("StockPrice table record count: " + count);
    }
}

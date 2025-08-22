package com.stock.repositories;

import com.stock.entities.StockPrice;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, String> {
}

package com.stock.infrastructure.repositories;

import com.stock.domain.entities.StockPrice;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, String> {
}

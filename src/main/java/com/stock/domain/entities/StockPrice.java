package com.stock.domain.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class StockPrice {

    @Id
    @NotBlank
    private String symbol;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal threshold;

    public StockPrice() {}

    public StockPrice(String symbol, BigDecimal price, BigDecimal threshold) {
        this.symbol = symbol;
        this.price = price;
        this.threshold = threshold;
    }

    public String getSymbol() { return symbol; }

    public void setSymbol(String symbol) { this.symbol = symbol; }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }
}
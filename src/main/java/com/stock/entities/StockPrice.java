package com.stock.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StockPrice {

    @Id
    private String symbol;

    private BigDecimal price;

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
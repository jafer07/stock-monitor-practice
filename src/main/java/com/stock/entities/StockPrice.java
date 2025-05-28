package com.stock.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StockPrice {

    @Id
    private String symbol;

    private double price;

    public StockPrice() {}

    public StockPrice(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
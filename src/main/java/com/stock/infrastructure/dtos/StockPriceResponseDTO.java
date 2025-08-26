package com.stock.infrastructure.dtos;

import java.math.BigDecimal;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class StockPriceResponseDTO {

    private String symbol;

    private BigDecimal price;

    private BigDecimal threshold;

    private boolean alert;

    public StockPriceResponseDTO(String symbol, BigDecimal price, BigDecimal threshold, boolean alert) {
        this.symbol = symbol;
        this.price = price;
        this.threshold = threshold;
        this.alert = alert;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public boolean isAlert() {
        return alert;
    }

}

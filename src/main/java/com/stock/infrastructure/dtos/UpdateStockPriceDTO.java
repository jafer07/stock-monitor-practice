package com.stock.infrastructure.dtos;


import java.math.BigDecimal;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public class UpdateStockPriceDTO {

    @NotBlank
    private String symbol;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal price;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal threshold;

    public UpdateStockPriceDTO() {
    }

    public UpdateStockPriceDTO(String symbol, BigDecimal price, BigDecimal threshold) {
        this.symbol = symbol;
        this.price = price;
        this.threshold = threshold;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }
}

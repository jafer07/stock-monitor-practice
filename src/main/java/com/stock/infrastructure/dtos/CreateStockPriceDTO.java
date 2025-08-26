package com.stock.infrastructure.dtos;

import java.math.BigDecimal;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

@Serdeable
@Introspected
public class CreateStockPriceDTO {

    @NotBlank
    private String symbol;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal initialPrice;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal threshold;

    public CreateStockPriceDTO() {
    }

    public CreateStockPriceDTO(String symbol, BigDecimal initialPrice, BigDecimal threshold) {
        this.symbol = symbol;
        this.initialPrice = initialPrice;
        this.threshold = threshold;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }
}

package com.stock.infrastructure.resources;

import java.net.URI;

import com.stock.business.services.StockPriceService;
import com.stock.infrastructure.dtos.CreateStockPriceDTO;
import com.stock.infrastructure.dtos.StockPriceResponseDTO;
import com.stock.infrastructure.dtos.UpdateStockPriceDTO;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@Validated
@Controller("/stocks")
public class StockResource {

    private final StockPriceService stockPriceService;

    public StockResource(StockPriceService stockPriceService) {
        this.stockPriceService = stockPriceService;
    }

    @Post
    public HttpResponse<StockPriceResponseDTO> addStock(
            @Body @Valid CreateStockPriceDTO createStockPriceDTO) {

        return stockPriceService.createStockPrice(createStockPriceDTO)
                .map(stockPriceResponseDTO -> HttpResponse.created(stockPriceResponseDTO, URI.create("/stocks/" + stockPriceResponseDTO.getSymbol())))
                .orElse(HttpResponse.status(HttpStatus.CONFLICT));
    }

    @Get("/{symbol}")
    public HttpResponse<StockPriceResponseDTO> getStock(String symbol) {
        return stockPriceService.getStock(symbol)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Put
    public HttpResponse<StockPriceResponseDTO> updateStock(
            @Body @Valid UpdateStockPriceDTO updateStockPriceDTO) {

        return stockPriceService.updateStock(updateStockPriceDTO)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Delete("/{symbol}")
    public HttpResponse<Void> deleteStock(String symbol) {
        boolean deleted = stockPriceService.deleteStock(symbol);
        if (deleted) {
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

}

package com.example.binance.controller;

import com.example.binance.service.BinanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BinanceController {

    private final BinanceService binanceService;

    public BinanceController(BinanceService binanceService) {
        this.binanceService = binanceService;
    }

    @GetMapping("/pairs/{symbol}")
    public Double getPrice(@PathVariable String symbol) {
        Double price = binanceService.getPrice(symbol.toUpperCase());
        if (price == null) {
            throw new RuntimeException("No price available for " + symbol);
        }
        return price;
    }


}

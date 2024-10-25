package com.example.binance.controller;

import com.example.binance.service.BinanceService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BinanceController {

    private BinanceService binanceService;

    public BinanceController(BinanceService binanceService) {
        this.binanceService = binanceService;
    }


}

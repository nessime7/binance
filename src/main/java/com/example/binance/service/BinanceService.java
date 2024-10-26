package com.example.binance.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class BinanceService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentHashMap<String, Double> priceCache = new ConcurrentHashMap<>();

    // This method processes the WebSocket message and updates the cache
    public void processMessage(String message) {
        try {
            // Parse the incoming JSON message
            JsonNode jsonNode = objectMapper.readTree(message);

            // Extract the relevant information
            String symbol = jsonNode.get("s").asText();  // The symbol (e.g., BTCUSDT, ETHUSDT)
            double price = jsonNode.get("c").asDouble(); // The latest price (field "c" is for last price)

            // Store the symbol and price in the cache
            priceCache.put(symbol.toUpperCase(), price);

            System.out.println("Received price update: Symbol = " + symbol + ", Price = " + price);

        } catch (Exception e) {
            // Handle parsing errors or other exceptions
            e.printStackTrace();
        }
    }

    // This method returns the price for the requested symbol from the cache
    public Double getPrice(String symbol) {
        // Fetch the price from the cache using the symbol
        return priceCache.get(symbol);
    }
}
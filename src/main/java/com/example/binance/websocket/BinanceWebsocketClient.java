package com.example.binance.websocket;

import com.example.binance.service.BinanceService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class BinanceWebsocketClient {

    private final BinanceService binanceService;

    private static final String API_KEY = "AgAlOZwnJJHjjehe3dtoALvAK67xmQSlsqGRxOULMLZyLEiyIEXAXNrhAzyEqc5P";

    public BinanceWebsocketClient(BinanceService binanceService) {
        this.binanceService = binanceService;
    }

    @PostConstruct
    public void connectToWebSocket() throws URISyntaxException {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("X-MBX-APIKEY", API_KEY); // Add API key to the headers

        // Connect to BTC-USD stream
        client.execute(new BinanceWebSocketHandler(), headers,
                new URI("wss://stream.binance.com:9443/ws/btcusdt@ticker"));

        // Connect to ETH-USD stream
        client.execute(new BinanceWebSocketHandler(), headers,
                new URI("wss://stream.binance.com:9443/ws/ethusdt@ticker"));
    }

    private class BinanceWebSocketHandler extends TextWebSocketHandler {
        @Override
        public void handleTextMessage(WebSocketSession session, org.springframework.web.socket.TextMessage message) {
            String payload = message.getPayload();
            // Process the received message (e.g., parse JSON and update cache)
            binanceService.processMessage(payload);
        }
    }
}

package com.stock.infrastructure.websocket;

import com.stock.infrastructure.dtos.StockPriceResponseDTO;

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import jakarta.inject.Singleton;

@ServerWebSocket("/ws/stocks")
@Singleton
public class StockWebSocket {

    private final WebSocketBroadcaster broadcaster;

    public StockWebSocket(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @OnOpen
    public void onOpen(WebSocketSession session) {
        // Optionally log or send initial data
        System.out.println("Client connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, WebSocketSession session) {
        System.out.println("Received from client " + session.getId() + ": " + message);
    }

    public void sendStockUpdate(StockPriceResponseDTO dto) {
        broadcaster.broadcastSync(dto, session -> true);
    }
}

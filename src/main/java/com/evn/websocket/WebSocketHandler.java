package com.evn.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String response = webSocketService.getDataFromDatabase();
        session.sendMessage(new TextMessage(response));
    }

    private void getDataFromDatabase() {
        // TODO
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connected: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Closed: " + session.getId());
    }
}


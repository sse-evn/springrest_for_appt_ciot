package com.evn.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.concurrent.CopyOnWriteArrayList;

@Component // WebSocketHandler теперь управляется Spring
public class WebSocketHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sendMessageToAll("Новое сообщение: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    // Метод для отправки сообщений всем клиентам
    public void sendMessageToAll(String message) {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

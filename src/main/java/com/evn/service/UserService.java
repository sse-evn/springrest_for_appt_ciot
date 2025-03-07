package com.evn.service;

import com.evn.model.User;
import com.evn.repository.UserRepository;
import com.evn.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebSocketHandler webSocketHandler; // Инжект WebSocketHandler

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) throws Exception {
        User savedUser = userRepository.save(user);
        webSocketHandler.sendMessageToAll("Пользователь добавлен: " + savedUser.getUsername()); // Отправляем WS-сообщение
        return savedUser;
    }
}

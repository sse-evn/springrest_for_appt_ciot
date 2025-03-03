package com.evn.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getDataFromDatabase() {
        return jdbcTemplate.queryForObject("SELECT data FROM your_table LIMIT 1", String.class);
    }
}

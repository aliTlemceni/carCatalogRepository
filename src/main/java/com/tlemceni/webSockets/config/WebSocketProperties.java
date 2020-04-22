package com.tlemceni.webSockets.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("app.websocket")
public class WebSocketProperties {
	
	/**
     * Prefix used for WebSocket destination mappings
     */
    private String applicationPrefix;
    /**
     * Prefix used by topics
     */
    private String topicPrefix;
    /**
     * Endpoint that can be used to connect to
     */
    private String endpoint;
    /**
     * Allowed origins
     */
    private String[] allowedOrigins = new String[0];

}

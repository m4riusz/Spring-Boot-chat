package com.msut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import static com.msut.config.Route.*;

/**
 * Created by mariusz on 05.02.17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class SecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(APP_PREFIX)
                .enableSimpleBroker(BROKER_PREFIXES);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint(WEB_SOCKET).withSockJS();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}

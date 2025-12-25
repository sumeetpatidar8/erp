package com.sumeet.erp.notifications;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.sumeet.erp.common.AppProperties;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final AppProperties appProperties;

  @Autowired
  public WebSocketConfig(AppProperties appProperties) {
    this.appProperties = Objects.requireNonNull(appProperties, "appProperties must not be null");
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    Objects.requireNonNull(config, "config must not be null");
    config.enableSimpleBroker(appProperties.getWebsocket().getBrokerPrefixes().toArray(new String[0]));
    config.setApplicationDestinationPrefixes(appProperties.getWebsocket().getAppPrefix());
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    Objects.requireNonNull(registry, "registry must not be null");
    registry.addEndpoint(appProperties.getWebsocket().getEndpoint())
            .setAllowedOriginPatterns("*")
            .withSockJS();
  }
}
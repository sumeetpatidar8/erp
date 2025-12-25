package com.sumeet.erp.notifications;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.sumeet.erp.common.AppProperties;

@Controller
public class AlertController {

  private final SimpMessagingTemplate messaging;
  private final AppProperties appProperties;

  @Autowired
  public AlertController(SimpMessagingTemplate messaging, AppProperties appProperties) {
    this.messaging = Objects.requireNonNull(messaging, "messaging must not be null");
    this.appProperties = Objects.requireNonNull(appProperties, "appProperties must not be null");
  }

  @MessageMapping("/alerts")
  public void inbound(AlertMessage msg) {
    if (msg == null || msg.companyId() == null) {
      return; // invalid message, skip
    }
    // fan-out with company scoping
    messaging.convertAndSend(appProperties.getAlerts().getTopicPrefix() + msg.companyId(), msg);
  }
}
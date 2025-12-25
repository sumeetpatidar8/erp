package com.sumeet.erp.common;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Component
public class AppProperties {

  private Security security = new Security();
  private Websocket websocket = new Websocket();
  private Alerts alerts = new Alerts();
  private Pagination pagination = new Pagination();
  private Time time = new Time();

  public Security getSecurity() { return security; }
  public void setSecurity(Security security) { this.security = security; }

  public Websocket getWebsocket() { return websocket; }
  public void setWebsocket(Websocket websocket) { this.websocket = websocket; }

  public Alerts getAlerts() { return alerts; }
  public void setAlerts(Alerts alerts) { this.alerts = alerts; }

  public Pagination getPagination() { return pagination; }
  public void setPagination(Pagination pagination) { this.pagination = pagination; }

  public Time getTime() { return time; }
  public void setTime(Time time) { this.time = time; }

  public static class Security {
    private List<String> permittedPaths = List.of("/login", "/websocket/**", "/assets/**");

    public List<String> getPermittedPaths() { return permittedPaths; }
    public void setPermittedPaths(List<String> permittedPaths) { this.permittedPaths = permittedPaths; }
  }

  public static class Websocket {
    private String endpoint = "/websocket";
    private List<String> brokerPrefixes = List.of("/topic", "/queue");
    private String appPrefix = "/app";

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public List<String> getBrokerPrefixes() { return brokerPrefixes; }
    public void setBrokerPrefixes(List<String> brokerPrefixes) { this.brokerPrefixes = brokerPrefixes; }

    public String getAppPrefix() { return appPrefix; }
    public void setAppPrefix(String appPrefix) { this.appPrefix = appPrefix; }
  }

  public static class Alerts {
    private String topicPrefix = "/topic/alerts.";

    public String getTopicPrefix() { return topicPrefix; }
    public void setTopicPrefix(String topicPrefix) { this.topicPrefix = topicPrefix; }
  }

  public static class Pagination {
    private int defaultSize = 20;
    private int maxSize = 200;
    private String defaultSort = "id,desc";

    public int getDefaultSize() { return defaultSize; }
    public void setDefaultSize(int defaultSize) { this.defaultSize = defaultSize; }

    public int getMaxSize() { return maxSize; }
    public void setMaxSize(int maxSize) { this.maxSize = maxSize; }

    public String getDefaultSort() { return defaultSort; }
    public void setDefaultSort(String defaultSort) { this.defaultSort = defaultSort; }
  }

  public static class Time {
    private String defaultZone = "UTC";

    public String getDefaultZone() { return defaultZone; }
    public void setDefaultZone(String defaultZone) { this.defaultZone = defaultZone; }
  }
}
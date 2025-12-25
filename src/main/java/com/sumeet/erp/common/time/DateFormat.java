package com.sumeet.erp.common.time;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumeet.erp.common.AppProperties;

@Component
public class DateFormat {

  private final ZoneId defaultZone;

  @Autowired
  public DateFormat(AppProperties appProperties) {
    Objects.requireNonNull(appProperties, "appProperties must not be null");
    this.defaultZone = ZoneId.of(appProperties.getTime().getDefaultZone());
  }

  public String formatInstant(Instant instant, ZoneId zone) {
    Objects.requireNonNull(instant, "instant must not be null");
    Objects.requireNonNull(zone, "zone must not be null");
    return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(instant.atZone(zone));
  }

  public String formatInstant(Instant instant) {
    Objects.requireNonNull(instant, "instant must not be null");
    return formatInstant(instant, defaultZone);
  }

  public Instant parseIso(String iso) {
    Objects.requireNonNull(iso, "iso must not be null");
    return OffsetDateTime.parse(iso).toInstant();
  }
}
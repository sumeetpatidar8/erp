package com.sumeet.erp.common.web;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumeet.erp.common.AppProperties;

@Component
public class Pagination {

  private final int defaultSize;
  private final int maxSize;
  private final String defaultSort;

  @Autowired
  public Pagination(AppProperties appProperties) {
    Objects.requireNonNull(appProperties, "appProperties must not be null");
    this.defaultSize = appProperties.getPagination().getDefaultSize();
    this.maxSize = appProperties.getPagination().getMaxSize();
    this.defaultSort = appProperties.getPagination().getDefaultSort();
  }

  public record Page(int page, int size, String sort) {
    public static Page of(Integer page, Integer size, String sort, int defaultSize, int maxSize, String defaultSort) {
      return new Page(
        page == null || page < 0 ? 0 : page,
        size == null || size <= 0 ? defaultSize : Math.min(size, maxSize),
        sort == null ? defaultSort : sort
      );
    }
  }

  public Page of(Integer page, Integer size, String sort) {
    return Page.of(page, size, sort, defaultSize, maxSize, defaultSort);
  }
}
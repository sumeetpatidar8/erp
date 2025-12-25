package com.sumeet.erp.common.format;

public final class StringFormat {
  private StringFormat() {}

  public static String slug(String input) {
    String s = input == null ? "" : input.trim().toLowerCase();
    s = s.replaceAll("[^a-z0-9]+", "-");
    return s.replaceAll("^-+|-+$", "");
  }

  public static String normalizeSpace(String input) {
    return input == null ? "" : input.trim().replaceAll("\\s+", " ");
  }
}
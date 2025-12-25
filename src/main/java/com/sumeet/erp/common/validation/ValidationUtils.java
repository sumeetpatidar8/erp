package com.sumeet.erp.common.validation;

public final class ValidationUtils {
  private ValidationUtils() {}

  public static boolean isEmail(String s) {
    return s != null && s.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
  }

  public static void require(boolean condition, String message) {
    if (!condition) throw new IllegalArgumentException(message);
  }

  public static String nonBlank(String s, String field) {
    if (s == null || s.isBlank()) throw new IllegalArgumentException(field + " must not be blank");
    return s.trim();
  }
}
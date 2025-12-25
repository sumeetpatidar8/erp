package com.sumeet.erp.common.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ValidationUtilsTest {

  @Test
  void testIsEmail() {
    assertTrue(ValidationUtils.isEmail("test@example.com"));
    assertFalse(ValidationUtils.isEmail("invalid"));
    assertFalse(ValidationUtils.isEmail(null));
  }

  @Test
  void testRequire() {
    assertDoesNotThrow(() -> ValidationUtils.require(true, "message"));
    assertThrows(IllegalArgumentException.class, () -> ValidationUtils.require(false, "error"));
  }

  @Test
  void testNonBlank() {
    assertEquals("test", ValidationUtils.nonBlank("test", "field"));
    assertEquals("test", ValidationUtils.nonBlank(" test ", "field"));
    assertThrows(IllegalArgumentException.class, () -> ValidationUtils.nonBlank("", "field"));
    assertThrows(IllegalArgumentException.class, () -> ValidationUtils.nonBlank(null, "field"));
  }
}
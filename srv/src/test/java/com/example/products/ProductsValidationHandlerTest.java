package com.example.products;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.sap.cds.services.ServiceException;

class ProductsValidationHandlerTest {

  @Test
  void acceptsZeroAndPositiveValues() {
    assertDoesNotThrow(() -> ProductsValidationHandler.validateValues(
        Map.of("price", BigDecimal.ZERO, "stock", 0)));
    assertDoesNotThrow(() -> ProductsValidationHandler.validateValues(
        Map.of("price", new BigDecimal("1250.50"), "stock", 3)));
  }

  @Test
  void rejectsNegativePrice() {
    ServiceException exception = assertThrows(ServiceException.class,
        () -> ProductsValidationHandler.validateValues(Map.of("price", new BigDecimal("-0.01"))));

    assertEquals("価格は0以上で入力してください。", exception.getMessage());
  }

  @Test
  void rejectsNegativeStock() {
    ServiceException exception = assertThrows(ServiceException.class,
        () -> ProductsValidationHandler.validateValues(Map.of("stock", -1)));

    assertEquals("在庫数は0以上で入力してください。", exception.getMessage());
  }

  @Test
  void allowsFieldsToBeOmittedOrNullDuringPartialUpdates() {
    assertDoesNotThrow(() -> ProductsValidationHandler.validateValues(Map.of()));
    Map<String, Object> values = new HashMap<>();
    values.put("price", null);
    assertDoesNotThrow(() -> ProductsValidationHandler.validateValues(values));
  }
}

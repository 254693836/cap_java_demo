package com.example.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CheckProductHandlerTest {

  @Test
  void returnsExistingMessageWhenProductExists() {
    var result = CheckProductHandler.resultFor(true);

    assertEquals(true, result.get("productExists"));
    assertEquals("データはDBに存在します", result.get("message"));
  }

  @Test
  void returnsMissingMessageWhenProductDoesNotExist() {
    var result = CheckProductHandler.resultFor(false);

    assertEquals(false, result.get("productExists"));
    assertEquals("データはDBに存在しません", result.get("message"));
  }
}

package com.example.products;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.persistence.PersistenceService;

import catalogservice.CatalogService_;
import catalogservice.Products;
import catalogservice.Products_;

import static com.sap.cds.ql.Select.from;

/** Handles the Object Page's bound Check action. */
@Component
public class CheckProductHandler implements EventHandler {
  private final PersistenceService db;

  CheckProductHandler(PersistenceService db) {
    this.db = db;
  }

  @On(event = "check", entity = Products_.CDS_NAME, service = CatalogService_.CDS_NAME)
  public Map<String, Object> check(Products product) {
    boolean exists = product != null && product.getId() != null
        && db.run(from(Products_.class).byId(product.getId())).first().isPresent();
    return Map.of(
        "productExists", exists,
        "message", exists ? "データはDBに存在します" : "データはDBに存在しません");
  }
}

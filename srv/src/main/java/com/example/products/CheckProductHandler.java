package com.example.products;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.persistence.PersistenceService;

import catalogservice.CatalogService_;
import catalogservice.Products_;

/** Handles the Object Page's bound Check action. */
@Component
public class CheckProductHandler implements EventHandler {
  private final PersistenceService db;

  CheckProductHandler(PersistenceService db) {
    this.db = db;
  }

  @On(event = "check", entity = Products_.CDS_NAME, service = CatalogService_.CDS_NAME)
  public void check(CheckProductContext context) {
    boolean exists = db.run(context.getCqn()).first().isPresent();
    context.setResult(Map.of(
        "productExists", exists,
        "message", exists ? "データはDBに存在します" : "データはDBに存在しません"));
  }
}

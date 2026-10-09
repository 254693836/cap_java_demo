package com.example.products;

import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.persistence.PersistenceService;

import catalogservice.CatalogService_;
import catalogservice.CheckContext;
import catalogservice.CheckResult;
import catalogservice.Products_;

/** Handles the Object Page's bound Check action. */
@Component
public class CheckProductHandler implements EventHandler {
  private final PersistenceService db;

  CheckProductHandler(PersistenceService db) {
    this.db = db;
  }

  @On(event = CheckContext.CDS_NAME, entity = Products_.CDS_NAME, service = CatalogService_.CDS_NAME)
  public void check(CheckContext context) {
    boolean exists = db.run(context.getCqn()).first().isPresent();
    CheckResult result = CheckResult.create();
    result.setProductExists(exists);
    result.setMessage(exists ? "データはDBに存在します" : "データはDBに存在しません");
    context.setResult(result);
  }
}

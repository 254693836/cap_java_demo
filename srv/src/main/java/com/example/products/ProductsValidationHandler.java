package com.example.products;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.draft.DraftNewEventContext;
import com.sap.cds.services.draft.DraftPatchEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;

import catalogservice.CatalogService_;
import catalogservice.Products_;

@Component
public class ProductsValidationHandler implements EventHandler {

  @Before(event = "CREATE", entity = Products_.CDS_NAME, service = CatalogService_.CDS_NAME)
  public void validateCreate(CdsCreateEventContext context) {
    context.getCqn().entries().forEach(ProductsValidationHandler::validateValues);
  }

  @Before(event = "UPDATE", entity = Products_.CDS_NAME, service = CatalogService_.CDS_NAME)
  public void validateUpdate(CdsUpdateEventContext context) {
    validateValues(context.getCqn().data());
    context.getCqnValueSets().forEach(ProductsValidationHandler::validateValues);
  }

  @Before(event = DraftService.EVENT_DRAFT_NEW, entity = Products_.CDS_NAME, service = CatalogService_.CDS_NAME)
  public void validateDraftCreate(DraftNewEventContext context) {
    context.getCqn().entries().forEach(ProductsValidationHandler::validateValues);
  }

  @Before(event = DraftService.EVENT_DRAFT_PATCH, entity = Products_.CDS_NAME, service = CatalogService_.CDS_NAME)
  public void validateDraftUpdate(DraftPatchEventContext context) {
    validateValues(context.getCqn().data());
    context.getCqnValueSets().forEach(ProductsValidationHandler::validateValues);
  }

  static void validateValues(Map<String, ?> values) {
    validateNonNegative(values, "price", "価格");
    validateNonNegative(values, "stock", "在庫数");
  }

  private static void validateNonNegative(Map<String, ?> values, String field, String label) {
    Object value = values.get(field);
    if (value == null) {
      return;
    }
    if (!(value instanceof Number number)) {
      throw new ServiceException(ErrorStatuses.BAD_REQUEST, label + "は数値で入力してください。");
    }
    if (new BigDecimal(number.toString()).signum() < 0) {
      throw new ServiceException(ErrorStatuses.BAD_REQUEST, label + "は0以上で入力してください。");
    }
  }
}

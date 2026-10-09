package com.example.products;

import java.util.Map;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;

/** Typed context for the bound Products.check action. */
@EventName("check")
public interface CheckProductContext extends EventContext {
  CqnSelect getCqn();

  void setCqn(CqnSelect cqn);

  void setResult(Map<String, Object> result);
}

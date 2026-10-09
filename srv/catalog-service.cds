using { com.example.products as db } from '../db/schema';

@path: '/catalog'
service CatalogService {
  @odata.draft.enabled
  entity Products as projection on db.Products actions {
    action check() returns CheckResult;
  };

  type CheckResult {
    productExists : Boolean;
    message : String;
  };
}

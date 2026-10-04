using { com.example.products as db } from '../db/schema';

@path: '/odata/v4/catalog'
service CatalogService {
  @odata.draft.enabled
  entity Products as projection on db.Products actions {
    action check() returns CheckResult;
  };

  type CheckResult {
    ![exists] : Boolean;
    message : String;
  };
}

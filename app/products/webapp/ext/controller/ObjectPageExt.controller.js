sap.ui.define(["sap/ui/core/mvc/ControllerExtension", "sap/m/MessageBox"], function (ControllerExtension, MessageBox) {
  "use strict";
  return ControllerExtension.extend("com.example.products.ext.controller.ObjectPageExt", {
    override: {
      onAfterBinding: function () {
        // The bound OData action is exposed by the backend as Products_check.
      }
    },
    onCheckProduct: async function () {
      const context = this.base.getBindingContext();
      const action = context.getModel().bindContext(context.getPath() + "/com.example.products.CatalogService.check(...)");
      await action.execute();
      const result = action.getBoundContext().getObject();
      MessageBox.information(result.message);
    }
  });
});

sap.ui.define([
  "sap/ui/core/mvc/ControllerExtension",
  "sap/m/MessageBox"
], function (ControllerExtension, MessageBox) {
  "use strict";
  return ControllerExtension.extend("com.example.products.ext.controller.ObjectPageExt", {
    onCheckProduct: async function () {
      const context = this.base.getBindingContext();
      const resourceBundle = this.base.getView().getModel("i18n").getResourceBundle();

      if (!context) {
        MessageBox.error(resourceBundle.getText("checkProductNoContext"));
        return;
      }

      try {
        const action = context.getModel().bindContext(
          context.getPath() + "/CatalogService.check(...)"
        );
        await action.execute();
        const result = action.getBoundContext().getObject();
        MessageBox.information(result.message, {
          title: resourceBundle.getText("checkProductTitle")
        });
      } catch (error) {
        MessageBox.error(resourceBundle.getText("checkProductFailed"));
      }
    }
  });
});

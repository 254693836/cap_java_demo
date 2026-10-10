using CatalogService as service from './catalog-service';

annotate service.Products with @(
  UI.HeaderInfo: { TypeName: '商品', TypeNamePlural: '商品', Title: { Value: name }, Description: { Value: category } },
  UI.SelectionFields: [ category, name ],
  UI.PresentationVariant: {
    SortOrder: [ { Property: name, Descending: false } ]
  },
  UI.LineItem: [
    { Value: name, Label: '商品名' }, { Value: category, Label: 'カテゴリ' },
    { Value: price, Label: '価格' }, { Value: currency, Label: '通貨' }, { Value: stock, Label: '在庫数' }
  ],
  UI.Identification: [
    { Value: name, Label: '商品名' }, { Value: description, Label: '説明' },
    { Value: category, Label: 'カテゴリ' }, { Value: price, Label: '価格' },
    { Value: currency, Label: '通貨' }, { Value: stock, Label: '在庫数' }
  ]
);

annotate service.Products with {
  price @Measures.ISOCurrency: currency;
};

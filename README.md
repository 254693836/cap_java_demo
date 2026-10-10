# CAP Java 商品管理サンプル

CAP Java（OData V4）と SAP Fiori elements を使った商品管理のサンプルです。

## 機能

- 商品の一覧表示、登録、更新（Fiori elements の List Report / Object Page）
- 商品登録・更新時の価格と在庫数の非負チェック（違反時は日本語メッセージを表示）
- ローカル H2 データベースと CSV 初期データ
- Object Page から商品がDBに存在することを確認する `Check` バインドアクション
  - 存在時: `データはDBに存在します`
  - 不存在時: `データはDBに存在しません`

## 実装方針

- CDS: 商品エンティティ、OData V4 サービス、一覧／Object Page の annotation を定義します。
- CAP Java: 標準 CRUD は CAP に任せ、価格・在庫数の業務チェックと `check` action だけを実装します。
- Fiori elements: 一覧・登録・更新は標準の List Report／Object Page を利用します。`Check` の結果ポップアップだけを UI5 controller extension で実装します。
- 一覧は商品名の昇順で初期表示し、価格は通貨コードを使って表示します。

## テスト

```bash
mvn -pl srv test
```

テストでは、価格・在庫数の入力チェックと `check` action が返す存在有無メッセージを確認します。

## 起動

Java 21、Maven 3.9 以降、Node.js 20 以降が必要です。

```bash
git pull origin main
rm -rf srv/src/gen srv/target
npm install
npm run build
mvn -pl srv spring-boot:run
```

別のターミナルで Fiori アプリを起動します。

```bash
cd app/products
npm install
npm start
※①basで起動できないなら：npx ui5 serve --port 8081
※②ブラウザで Ctrl + Shift + R
```

バックエンドの OData メタデータは `http://localhost:8080/odata/v4/catalog/$metadata` で確認できます。

CAP の CDS モデルから CSN を生成するため、`mvn -pl srv spring-boot:run` の前に `npm run build` を実行してください。H2 用の `schema.sql` は Maven 起動時に CDS モデルから生成され、Spring Boot の起動時に適用されます。

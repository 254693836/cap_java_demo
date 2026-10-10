# CAP Java 商品管理サンプル

CAP Java（OData V4）と SAP Fiori elements を使った商品管理のサンプルです。

## 機能

- 商品の一覧表示、登録、更新（Fiori elements の List Report / Object Page）
- 商品登録・更新時の価格と在庫数の非負チェック（違反時は日本語メッセージを表示）
- ローカル H2 データベースと CSV 初期データ
- Object Page から商品がDBに存在することを確認する `Check` バインドアクション
  - 存在時: `データはDBに存在します`
  - 不存在時: `データはDBに存在しません`

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

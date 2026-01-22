## バグ: 日本語の出力が極端に少なくなる

```java
var preparation_data = is_jp ? new PreparationEn(lst.toArray(Path[]::new))
    : new PreparationJp(lst.toArray(Path[]::new));
```

`is_jp`の条件分岐が逆転していた。そのため英語文章に対して日本語の`.Parse()`が適用され正しい学習ができていなかった。

## 依存: libのインポートができない

javaの仕様変更によるものだった。
.classxxxを編集して修正

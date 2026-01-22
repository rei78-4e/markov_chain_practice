---
marp: true
theme: default
size: 4:3
paginate: true
header: "Javaグループ課題 Markov chain with java"
footer: "内山, 今"
style: |
  section {
    display: flex;
    flex-direction: column;
    font-family: 'Helvetica Neue', Arial, 'Noto Sans JP', 'Meiryo', sans-serif;
  }
  h1 {
    font-family: 'Yu Mincho', 'Hiragino Mincho ProN', serif;
    color: #333;
  }
  section.title h1 {
    font-size: 50px;
  }
  .columns {
    display: flex;
    align-items: center;
    flex-grow: 1;
    overflow: hidden;
    gap: 10px;
  }
  .columns > div {
    flex: 1;
  }
  img {
    max-width: 100%;
    max-height: 100%;
    object-fit: contain;
  }
  .toc {
    column-count: 2;
    column-gap: 50px;
  }
  .toc li {
    font-size: 24px; /* 必要に応じて調整 */
    margin-bottom: 8px;
  }
  code {
    background: #f4f4f4;
    padding: 2px 4px;
    border-radius: 4px;
  }
  footer {
    left: 40px;
  }
---

# レビュー

## 内山 航平(25A4111026), 今 蒼月(25A4111068)

---

## バグ: 日本語の出力が極端に少なくなる

```java
var preparation_data = is_jp ? new PreparationEn(lst.toArray(Path[]::new))
    : new PreparationJp(lst.toArray(Path[]::new));
```

`is_jp`の条件分岐が逆転していた。そのため英語文章に対して日本語の`.Parse()`が適用され正しい学習ができていなかった。

## 依存: libのインポートができない

javaの仕様変更によるものだった。.classxxxを編集して修正

---

## 授業で使った（習った）制御構文などを使わなかった

swichやvar(型推論)など授業で扱わなかった処理を積極的に扱った、これは良くも悪くもjavaらしくないコードになっている。

## 警戒?: 生成出力時にスタックオーバーフロー

1回での生成長を5000より大きくすると発生する。出力時に再帰しているため、関数のスタックがオーバーフローしてしまう。

---

## 全体的に責任分散が出来ている

読み込みや計算、生成で処理のレイヤーごとにclassを分割しメンテンス性を向上させている。 またPreparationの抽象化により多言語対応を容易にしている。

## Java規約や文化に沿ってない

javaの標準的なコーディング規約と異なる点があり、他の開発者への可読性が下がってしまっている
例　parse_option -> parseOption など

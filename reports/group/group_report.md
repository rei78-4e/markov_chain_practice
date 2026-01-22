---
marp: true
theme: default
size: 4:3
paginate: true
header: "Javaグループ課題 Markov chain practice with java"
footer: "内山, 今"
style: |
  section {
    display: flex;
    flex-direction: column;
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
---

# 自由制作課題（グループ）

## 内山 航平(25A4111026), 今 蒼月(25A4111068)

---

## 目次

<div class="toc">

- [1. Main ソースコード(内山 担当)](#1-main-ソースコード内山-担当)
- [1.1. メインロジック](#11-メインロジック)
- [2. PreparationJp(日本語対応) (今 担当)](#2-preparationjp日本語対応-今-担当)
- [2.1. Preparationを継承した日本語用のデータ準備クラス](#21-preparationを継承した日本語用のデータ準備クラス)
- [3. Probability (内山 担当)](#3-probability-内山-担当)
- [4. Learning (内山 担当)](#4-learning-内山-担当)
- [4.1. HashMapを用いた単語予測](#41-hashmapを用いた単語予測)
- [5. Generate (内山 担当)](#5-generate-内山-担当)
- [5.1. 再帰関数を用いたリアルタイム出力](#51-再帰関数を用いたリアルタイム出力)
- [5.2. Generate 結果](#52-generate-結果)
- [ソースコードについて](#ソースコードについて)

</div>

---

## 1. Main ソースコード(内山 担当)

<div class="columns">
<div>

![](./img/main1.png)
</div>

<div>

![](./img/main2.png)

</div>
<div>

---

### 1.1. メインロジック
- 主に`Preparation`,`Leaning`,`Generate`を用いてクラスにデータを変換していく
- それぞれのクラスは単一の責任を持つように設計し、処理の差し替えを容易化
- 全体的にCUIでも読みやすいように空行を用いたり`---`などの記号で可読性を向上

---

## 2. PreparationJp(日本語対応) (今 担当)

<div class="columns">
<div>

![](./img/PreJp.png)

</div>
<div>

### 2.1. Preparationを継承した日本語用のデータ準備クラス
- `Parse()`メソッドをオーバーライドすることで日本語特有の単語分割に処理を置き換え
- それ以外の処理はすべて親クラスに依存
- Kkuromoji libを利用することで意味的な日本語の単語分割を実現

</div>
</div>

---

## 3. Probability (内山 担当)

<div class="columns">
<div>

![](./img/probability.png)

</div>
<div>

- 確率情報を分数として保持するためのクラス
- 小数点数で保持すると精度の問題で厳密でなくなるため、分数として保持することに

</div>
</div>

--- 

## 4. Learning (内山 担当)

<div class="columns">
<div>

![](./img/lean.png)

</div>
<div>

### 4.1. HashMapを用いた単語予測

- `HashMap<現在の単語, HashMap<次の単語, 出現回数>>`となるようにカウントを行う
- メソッドを用いることで単語の出現確率演算をシンプルに記述

</div>
</div>

--- 

## 5. Generate (内山 担当)

<div class="columns">
<div>

![](./img/gene.png)

</div>

<div>

### 5.1. 再帰関数を用いたリアルタイム出力

- 再帰しながら常に出力を行うことでリアルタイムに出力を行う

</div>
</div>

--- 

### 5.2. Generate 結果

<div class="columns">

![](./img/res.png)

</div>

---

## ソースコードについて

現在githubのprivate repositoryにソースコード全体がアップロードされています。
uliboulibo@gmail.comにgithubアカウントのidと共にご連絡頂ければ、こちらでrepositoryに招待し、ソースコードを閲覧できるようにいたします。
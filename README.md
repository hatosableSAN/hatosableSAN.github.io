# 鳴潮 感想ブログ

このリポジトリは、ソシャルゲーム「鳴潮」のプレイ感想や考察を綴るブログサイトです。

## フォルダ構成

```
.
├── index.html          # メインページ
├── style.css           # スタイルシート
├── image/              # 画像フォルダ
└── README.md           # このファイル
```

## 使用方法

1. `index.html`を編集して感想記事を追加してください
2. 画像は`image/`フォルダに配置してください
3. HTMLの`<img>`タグで`image/`フォルダ内の画像を参照してください

### 記事の追加例

```html
<article class="post">
    <h2>記事のタイトル</h2>
    <p class="date">2026-01-19</p>
    <img src="image/screenshot.jpg" alt="説明" class="post-image">
    <p>ここに感想を書きます...</p>
</article>
```

## GitHub Pages設定

このサイトはGitHub Pagesでホストされています。`main`ブランチに変更をプッシュすると自動的に公開されます。

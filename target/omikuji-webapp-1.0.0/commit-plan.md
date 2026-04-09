# commit-plan.md

このファイルは、おみくじアプリの改善を1つずつ実装し、各ステップで Git コミットを残すための計画書です。

## 0. 現状確認
- [ ] `git status` で作業ツリーを確認
- [ ] `git diff` で現状差分を確認
- [ ] 初期状態をコミット済みなら次へ

## 1. ロジック関数化
目的: コードの可読性と保守性を高める

変更内容:
- `getOmikuji()` 関数を追加
- `getLuckyItem()` 関数を追加
- `showResult(name, omikuji, item)` 関数を追加
- `showError(message)` 関数を追加
- `drawButton` のクリック処理を上記関数を使うように整理

コマンド:
- `git add index.html`
- `git commit -m "refactor: おみくじロジックを関数化"`

## 2. 運勢別色付け
目的: 結果を視認しやすくする

変更内容:
- CSS に `.daikichi`, `.chukichi`, `.shokichi`, `.kyou` を追加
- `showResult` で結果に応じたクラス付与

コマンド:
- `git add index.html`
- `git commit -m "style: 運勢に応じて結果表示色を分ける"`

## 3. フェードインアニメーション
目的: UX を改善

変更内容:
- `.result` に `transition` / `opacity` / `transform` を追加
- `.result.show` クラスを追加
- 結果表示時に `.show` を付与

コマンド:
- `git add index.html`
- `git commit -m "enhancement: 結果フェードインアニメーション追加"`

## 4. 履歴表示（セッション）
目的: 実行履歴を画面に残す

変更内容:
- HTML に `#historyList` 箇所を追加
- `addHistory(name, omikuji, item)` 関数を実装
- 直近履歴を表示、最大7件などで制御

コマンド:
- `git add index.html`
- `git commit -m "feat: 引いた履歴を表示"`

## 5. 入力チェック & アクセシビリティ
目的: ユーザー入力エラーを防ぎ、スクリーンリーダを考慮

変更内容:
- `<input id="name" required maxlength="20">` 追加
- `aria-live="polite"` や `role="status"` を結果要素に追加
- エラー時の視覚/読み上げ対応強化

コマンド:
- `git add index.html`
- `git commit -m "chore: 名前入力のバリデーションとアクセシビリティ対応"`

## 6. 運勢説明文の追加
目的: 結果に説明を加えて楽しさ向上

変更内容:
- `const descriptions = { ... }` オブジェクトを追加
- `showResult` に説明テキストを付与

コマンド:
- `git add index.html`
- `git commit -m "feat: 運勢ごとの詳細説明を追加"`

## 7. UI ビジュアル改善
目的: デザイン向上（見た目の完成度）

変更内容:
- ボタン、カード、背景のグラデーション
- レスポンシブ幅/フォント調整
- ホバー効果と影

コマンド:
- `git add index.html`
- `git commit -m "style: UIデザインをグラデーション&カード風に改善"`

## 8. 追加メモ
- 各ステップ実施後、`git log --oneline` で履歴を確認
- `git push` でリモートへ反映
- 必要に応じて `feature/improve-omikuji` などのブランチ運用を推奨

# **Gitでのルール**

<br />

## 基本的な流れ


### 1. ローカルリポジトリを最新の状態に更新する

自分のブランチにいない場合は移動してから実行する
> ※自分のブランチを作っていない場合は先に次の「作業の開始」    
> で自分のブランチを作ってから

```powershell
# ローカルリポジトリにあるbranchを確認
git branch

# -a を付けるとリモートリポジトリのbranchも確認できる
git branch -a

# 自分のブランチへ移動
git checkout 自分のブランチ名
```

リモートリポジトリの最新の状態を取得して適応させる

```powershell
# pullは git fetch + git mergeを同時に行う
git pull origin master:自分のブランチ名
```

### 2. 作業の開始

<br />

作業ブランチの作成
>基本的にmaster branchは触らず自分の作業リポジトリで作業する

```powershell
git branch ブランチ名
```

ブランチの移動

```powershell
git checkout ブランチ名
```

ブランチを作成して移動

```powershell
git checkout -b ブランチ名
```

### 3. 開発

### 4. 変更内容をステージング

変更内容をローカルのインデックスに登録
> vscodeの場合拡張機能があれば画面上で変更をステージできる

```powershell
# 直接指定する場合はそのファイルを相対パスを記述する
git add . # . を付けると全ての変更をステージング
```

### 5. ローカルリポジトリに変更を適応させる

コミットするタイミングは区切りがついたタイミングで行う。    
>例   
> 1つの機能が完成した   
> 以前完成した機能をアップデートした    
> 機能は完成していないが今日はもう開発を進めない    

<font color=red>プログラムにエラーが表示されている場合はコミットせず    
エラーが表示しないようにしてからコミットする</font>

<br />

ステージングされたファイル等をコミットする
> vscodeの場合拡張機能があれば画面上で変更をコミットできる

```powershell
# "" の中に変更する内容を記述する
git commit -m ""
# 例 git commit -m "共通するナビゲーションを作成"
```

### 6. リモートリポジトリに変更を知らせる

ローカルリポジトリのコミットをリモートリポジトリに送信する

```powershell
# ブランチ名は作業したブランチ名
git push -u origin ブランチ名
```

### 6. pull requestsの作成

pushした内容をmaster branchに統合するにはpull requestsを作成し、mergeする必要があります。   

1. Git hub上の`Pull requests`を選択
2. `Create pull request`を押す
3. 自分のpushしたcommitを押す
3. 何か補足の内容があれば`Write`に記述
4. `Create pull request`を押す

### 7. 自分のリポジトリを最新の状態に更新する

pull requestがmergeされた後ローカルリポジトリの内容を   
最新の状態に更新する

```powershell
git pull origin master:自分のブランチ名
```
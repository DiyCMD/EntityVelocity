# EntityMotion

任意のエンティティの Motion を変更するコマンドを追加するだけの Spigot プラグイン

## 対応バージョン

-   1.21

## 使い方

### 速度を小数で設定

`<x>`,`<y>`,`<z>`にそれぞれ速度を指定  
少数を使うことが可能

```mcfunction
/evelocity <target> setf <x> <y> <z>
```

### 速度を整数で設定

`<x>`,`<y>`,`<z>`にそれぞれ速度の 10000 倍を指定  
整数のみ使用可能

```mcfunction
/evelocity <target> seti <x> <y> <z>
```

# オブジェクト指向エキササイズのためのクソコード
- このプロジェクトはプログラミング言語論の演習課題「オブジェクト指向エキササイズ」のためのクソコードである．
- オブジェクト指向エキササイズについては下記参照のこと．下記書籍及びスライドに書かれている9つのエキササイズを実施することが目標．
 - ThoughtWorks Inc. (著), 株式会社オージス総研 オブジェクトの広場編集部 (翻訳), ThoughtWorksアンソロジー ―アジャイルとオブジェクト指向によるソフトウェアイノベーション 単行本（ソフトカバー），オライリー・ジャパン，2008/12/27. https://www.amazon.co.jp/dp/487311389X
 - 大圖衛玄，オブジェクト指向できていますか？, http://www.slideshare.net/MoriharuOhzu/ss-14083300, 2012/8/27.

## クソコード作成指針
何か良いのが思いついたら追加予定
- Collectionとパッケージを使わない
- できる限りクラスを作らない
- 作れるsetter/getterは全部作る
- ネストは深ければ深いほど良い
- 1つのメソッド内でできることを分割しない
- if-else if..は長ければ長い方が良い
- 変数名，メソッド名は適当につける
- 一行に付くドットの数の限界に挑戦したい（クラスを作らないと難しい．．．）
- 同じコードを何回でも書く

## KGO仕様
- Main.javaではKGOクラスをnewしたあと，繰り返しstartPhase()を呼び出し，player/cpuいずれかのHPが0以下になるまで繰り返す．
- initLibrary()はサーヴァントの初期化を行う．
- callServants()は全サーヴァントから3体のサーヴァント（重複あり）をPlayer/CPUそれぞれについて召喚し，サーヴァント毎に5枚ずつで合計15枚のカードからなるDeck（playerCards,cpuCardsの各配列）を構築する．
  - このメソッドは15枚のカードがなくなるたびに呼び出される．
- startPhase()では，下記の順に処理が行われる
  1. 各種GameStatus初期化（Draw済みカード等）
  1. Deckから3枚カードを引く
     - カードは3種類，Quick，Attack, Defence
     - Quickが2枚そろうと追加で1枚カードが増える（増えるカードはすでに引いた3枚のカードからランダムに選ばれる），3枚そろうと2枚カードが増える
     - Attackは3枚揃うと相手の防御力を無視した攻撃ができる，2枚の場合はAttack Pointが倍になる
     - Defenceは3枚そろうと相手の攻撃をカウンターする（防御無視攻撃もカウンターできる），2枚の場合はGuard Pointが2倍になる．
  1. カードそれぞれのポイントを決定する(全カードの合計ポイントがPlayer/CPUそれぞれのAttack PointとGuard Pointになる)
  1. Quick, Attack, Defenceのカード枚数をカウントし，特殊効果を付与する．
  1. それぞれのAttack Point，Guard Pointと特殊効果にもとづいて互いに攻撃を行い，それぞれのHPを減少させる
  1. HPが0以下になったら，その相手の勝ちとなり（両方同時に0以下になった場合は引き分け），終了する．
  1. Deckのカードが無くなった場合，callServants()を再度実行する．

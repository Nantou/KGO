import java.util.Random;

public class KGO {
  String[] servant = new String[5];
  int[] attackCards = new int[5];
  int[] quickCards = new int[5];
  int[] defenceCards = new int[5];

  int playerHp = 100;
  String[] playerCards = new String[15];
  String[] playerBattleCards = new String[5];
  int[] playerBattlePoints = new int[5];
  int playerAttackNum, playerQuickNum, playerDefenceNum;
  int playerAttackPoint, playerGuardPoint;
  String playerSpecialEffect = "";

  int cpuHp = 100;
  String[] cpuCards = new String[15];
  String[] cpuBattleCards = new String[5];
  int[] cpuBattlePoints = new int[5];
  int cpuAttackNum, cpuQuickNum, cpuDefenceNum;
  int cpuAttackPoint, cpuGuardPoint;
  String cpuSpecialEffect = "";

  Random rnd = new Random();

  public void startPhase() throws InterruptedException {
    // Game Status初期化
    for (int i = 0; i < this.playerBattleCards.length; i++) {
      this.playerBattleCards[i] = null;
      this.playerBattlePoints[i] = 0;
    }
    this.playerAttackNum = this.playerQuickNum = this.playerDefenceNum = 0;
    this.playerSpecialEffect = "";

    for (int i = 0; i < this.cpuBattleCards.length; i++) {
      this.cpuBattleCards[i] = null;
      this.cpuBattlePoints[i] = 0;
    }
    this.cpuAttackNum = this.cpuQuickNum = this.cpuDefenceNum = 0;
    this.cpuSpecialEffect = "";

    System.out.println("PlayerのDraw--------------");
    // Draw Phase
    // playerCardsで文字列がDoneでないものを先頭から3枚取得し
    // String playerBattleCards[5]に順に格納する
    // int playerBattlePoints[5]に順に1~5のポイントをランダムに選択して格納する
    for (int i = 0, j = 0; j < 3; i++) {
      if (this.playerCards[i].equals("Done")) {
        continue;
      } else {
        this.playerBattleCards[j] = this.playerCards[i];
        this.playerBattlePoints[j] = this.rnd.nextInt(5) + 1;
        this.playerCards[i] = "Done";
        j++;
      }
    }

    for (int i = 0; i < this.playerBattleCards.length; i++) {
      if (this.playerBattleCards[i] != null) {
        System.out.println(this.playerBattleCards[i]);
      }
    }
    Thread.sleep(2000);

    // Attack,Quick,Defenceのカード枚数計測
    this.playerAttackNum = 0;
    this.playerQuickNum = 0;
    this.playerDefenceNum = 0;
    for (int i = 0; i < this.playerBattleCards.length; i++) {
      if (this.playerBattleCards[i] == null) {
        continue;
      } else if (this.playerBattleCards[i].contains("Attack")) {
        this.playerAttackNum++;
      } else if (this.playerBattleCards[i].contains("Quick")) {
        this.playerQuickNum++;
      } else if (this.playerBattleCards[i].contains("Defence")) {
        this.playerDefenceNum++;
      }
    }

    // カード特殊効果の処理(Quickのみ)
    // Quickの特殊効果は各Turn最初の1回のみ発動する
    if (this.playerQuickNum >= 3) {// playerBattleCardsのうち2枚をコピーして追加
      System.out.println("Quick Chain3(Player) 手持ちのカードの中から2枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.playerBattleCards[3] = this.playerBattleCards[addCard1];
      this.playerBattlePoints[3] = this.playerBattlePoints[addCard1];
      int addCard2 = this.rnd.nextInt(3);
      this.playerBattleCards[4] = this.playerBattleCards[addCard2];
      this.playerBattlePoints[4] = this.playerBattlePoints[addCard2];
    } else if (this.playerQuickNum >= 2) {// playerBattleCardsのうち2枚をコピーして追加
      System.out.println("Quick Chain2(Player) 手持ちのカードの中から1枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.playerBattleCards[3] = this.playerBattleCards[addCard1];
      this.playerBattlePoints[3] = this.playerBattlePoints[addCard1];
    }

    // Attack,Quick,Defenceのカード枚数計測再び
    this.playerAttackNum = 0;
    this.playerQuickNum = 0;
    this.playerDefenceNum = 0;
    for (int i = 0; i < this.playerBattleCards.length; i++) {
      if (this.playerBattleCards[i] == null) {
        continue;
      } else if (this.playerBattleCards[i].contains("Attack")) {
        this.playerAttackNum++;
      } else if (this.playerBattleCards[i].contains("Quick")) {
        this.playerQuickNum++;
      } else if (this.playerBattleCards[i].contains("Defence")) {
        this.playerDefenceNum++;
      }
    }

    // Attack,Guard Pointの計算
    // battlePointsのポイント合計をAttack, Guard両方に加算
    for (int i = 0; i < this.playerBattlePoints.length; i++) {
      this.playerAttackPoint = this.playerAttackPoint + this.playerBattlePoints[i];
      this.playerGuardPoint = this.playerGuardPoint + this.playerBattlePoints[i];
    }

    // カード特殊効果の処理(Attack)
    if (this.playerAttackNum >= 3) {// 防御無視攻撃トリガーON
      System.out.println("PlayerのAttack Chain(3)! 防御無視攻撃！");
      this.playerSpecialEffect = "piercing"; // 防御無視
    } else if (this.playerAttackNum >= 2) {// attack point2倍
      System.out.println("PlayerのAttack Chain(2)! Attack Point2倍！");
      this.playerAttackPoint = this.playerAttackPoint * 2;
    }

    // カード特殊効果の処理(Defence)
    if (this.playerDefenceNum >= 3) {// フルカウンタートリガーON
      System.out.println("PlayerのDefence Chain(3)! フルカウンターモード！");
      this.playerSpecialEffect = "counter";
    } else if (this.playerDefenceNum >= 2) {// guard point2倍
      System.out.println("PlayerのDefence Chain(2)! Guard Point2倍！");
      this.playerGuardPoint = this.playerGuardPoint * 2;
    }
    System.out.println("PlayerのAttack/Guard Point--------------");
    System.out.println("AttackPoint:" + this.playerAttackPoint);
    System.out.println("GuardPoint:" + this.playerGuardPoint);
    Thread.sleep(3000);

    System.out.println("CPUのDraw--------------");
    // Draw Phase
    // cpuCardsで文字列がDoneでないものを先頭から3枚取得し
    // String cpuBattleCards[5]に順に格納する
    // int cpuBattlePoints[5]に順に1~5のポイントをランダムに選択して格納する
    for (int i = 0, j = 0; j < 3; i++) {
      if (this.cpuCards[i].equals("Done")) {
        continue;
      } else {
        this.cpuBattleCards[j] = this.cpuCards[i];
        this.cpuBattlePoints[j] = this.rnd.nextInt(5) + 1;
        this.cpuCards[i] = "Done";
        j++;
      }
    }

    for (int i = 0; i < this.cpuBattleCards.length; i++) {
      if (this.cpuBattleCards[i] != null) {
        System.out.println(this.cpuBattleCards[i]);
      }
    }
    Thread.sleep(2000);

    // Attack,Quick,Defenceのカード枚数計測
    this.cpuAttackNum = 0;
    this.cpuQuickNum = 0;
    this.cpuDefenceNum = 0;
    for (int i = 0; i < this.cpuBattleCards.length; i++) {
      if (this.cpuBattleCards[i] == null) {
        continue;
      } else if (this.cpuBattleCards[i].contains("Attack")) {
        this.cpuAttackNum++;
      } else if (this.cpuBattleCards[i].contains("Quick")) {
        this.cpuQuickNum++;
      } else if (this.cpuBattleCards[i].contains("Defence")) {
        this.cpuDefenceNum++;
      }
    }

    // カード特殊効果の処理(Quickのみ)
    // Quickの特殊効果は各Turn最初の1回のみ発動する
    if (this.cpuQuickNum >= 3) {// cpuBattleCardsのうち2枚をコピーして追加
      System.out.println("Quick Chain3(CPU) 手持ちのカードの中から2枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.cpuBattleCards[3] = this.cpuBattleCards[addCard1];
      this.cpuBattlePoints[3] = this.cpuBattlePoints[addCard1];
      int addCard2 = this.rnd.nextInt(3);
      this.cpuBattleCards[4] = this.cpuBattleCards[addCard2];
      this.cpuBattlePoints[4] = this.cpuBattlePoints[addCard2];
    } else if (this.cpuQuickNum >= 2) {// cpuBattleCardsのうち2枚をコピーして追加
      System.out.println("Quick Chain2(CPU) 手持ちのカードの中から1枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.cpuBattleCards[3] = this.cpuBattleCards[addCard1];
      this.cpuBattlePoints[3] = this.cpuBattlePoints[addCard1];
    }

    // Attack,Quick,Defenceのカード枚数計測再び
    this.cpuAttackNum = 0;
    this.cpuQuickNum = 0;
    this.cpuDefenceNum = 0;
    for (int i = 0; i < this.cpuBattleCards.length; i++) {
      if (this.cpuBattleCards[i] == null) {
        continue;
      } else if (this.cpuBattleCards[i].contains("Attack")) {
        this.cpuAttackNum++;
      } else if (this.cpuBattleCards[i].contains("Quick")) {
        this.cpuQuickNum++;
      } else if (this.cpuBattleCards[i].contains("Defence")) {
        this.cpuDefenceNum++;
      }
    }

    // Attack,Guard Pointの計算
    // battlePointsのポイント合計をAttack, Guard両方に加算
    for (int i = 0; i < this.cpuBattlePoints.length; i++) {
      this.cpuAttackPoint = this.cpuAttackPoint + this.cpuBattlePoints[i];
      this.cpuGuardPoint = this.cpuGuardPoint + this.cpuBattlePoints[i];
    }

    // カード特殊効果の処理(Attack)
    if (this.cpuAttackNum >= 3) {// 防御無視攻撃トリガーON
      System.out.println("CPUのAttack Chain(3)! 防御無視攻撃！");
      this.cpuSpecialEffect = "piercing"; // 防御無視
    } else if (this.cpuAttackNum >= 2) {// attack point2倍
      System.out.println("CPUのAttack Chain(2)! Attack Point2倍！");
      this.cpuAttackPoint = this.cpuAttackPoint * 2;
    }

    // カード特殊効果の処理(Defence)
    if (this.cpuDefenceNum >= 3) {// フルカウンタートリガーON
      System.out.println("CPUのDefence Chain(3)! フルカウンターモード！");
      this.cpuSpecialEffect = "counter";
    } else if (this.cpuDefenceNum >= 2) {// guard point2倍
      System.out.println("CPUのDefence Chain(2)! Guard Point2倍！");
      this.cpuGuardPoint = this.cpuGuardPoint * 2;
    }
    System.out.println("CPUのAttack/Guard Point--------------");
    System.out.println("AttackPoint:" + this.cpuAttackPoint);
    System.out.println("GuardPoint:" + this.cpuGuardPoint);
    Thread.sleep(3000);

    // バトル開始
    // Player Phase
    System.out.println("Playerの攻撃");
    Thread.sleep(500);
    if (this.playerSpecialEffect.isEmpty() == true) {// specialEffectがない場合，playerAttackpointからcpuAttackpointを引いたものをcpuHpから引く
      if (this.cpuSpecialEffect.isEmpty() == true) {
        if (this.playerAttackPoint - this.cpuGuardPoint >= 0) {
          this.cpuHp = this.cpuHp - (this.playerAttackPoint - this.cpuGuardPoint);
        }
      } else if (this.cpuSpecialEffect.contains("counter")) {// CPUがcounterモードの場合，playerAttackPoint分のダメージをplayerHPが受ける
        System.out.println("CPUのカウンター！");
        Thread.sleep(500);
        this.playerHp = this.playerHp - this.playerAttackPoint;
      }
    } else if (this.playerSpecialEffect.contains("piercing")) {// PlayerがpiercingモードでCPUがcounterモードでない場合，cpuのGuardPointを無視してcpuHpにダメージを与える
      System.out.println("Playerの防御無視攻撃！");
      Thread.sleep(500);
      if (this.cpuSpecialEffect.contains("counter")) {
        System.out.println("CPUのカウンター！");
        Thread.sleep(500);
        this.playerHp = this.playerHp - this.playerAttackPoint;
      } else {
        this.cpuHp = this.cpuHp - this.playerAttackPoint;
      }
    }

    // CPU Phase
    System.out.println("CPUの攻撃");
    Thread.sleep(500);
    if (this.cpuSpecialEffect.isEmpty() == true) {
      if (this.playerSpecialEffect.isEmpty() == true) {
        if (this.cpuAttackPoint - this.playerGuardPoint >= 0) {
          this.playerHp = this.playerHp - (this.cpuAttackPoint - this.playerGuardPoint);
        }
      } else if (this.playerSpecialEffect.contains("counter")) {
        System.out.println("Playerのカウンター！");
        Thread.sleep(500);
        this.cpuHp = this.cpuHp - this.cpuAttackPoint;
      }
    } else if (this.cpuSpecialEffect.contains("piercing")) {
      System.out.println("CPUの防御無視攻撃！");
      Thread.sleep(500);
      if (this.playerSpecialEffect.contains("counter")) {
        System.out.println("Playerのカウンター！");
        Thread.sleep(500);
        this.cpuHp = this.cpuHp - this.cpuAttackPoint;
      } else {
        this.playerHp = this.playerHp - this.cpuAttackPoint;
      }
    }
    System.out.println("Player HP:" + this.playerHp);
    System.out.println("CPU HP:" + this.cpuHp);

  }

  public void callServants() throws InterruptedException {

    // Create playerCards
    // ランダムな数値を出してservantを選ぶ
    int servant1 = rnd.nextInt(this.servant.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてplayerCardsを埋める
    int pcNum = 0;
    for (int i = 0; i < attackCards[servant1]; i++, pcNum++) {
      this.playerCards[pcNum] = "Attack:" + servant[servant1];
    }
    for (int i = 0; i < quickCards[servant1]; i++, pcNum++) {
      this.playerCards[pcNum] = "Quick:" + servant[servant1];
    }
    for (int i = 0; i < defenceCards[servant1]; i++, pcNum++) {
      this.playerCards[pcNum] = "Defence:" + servant[servant1];
    }

    int servant2 = rnd.nextInt(this.servant.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてplayerCardsを埋める
    for (int i = 0; i < attackCards[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Attack:" + servant[servant2];
    }
    for (int i = 0; i < quickCards[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Quick:" + servant[servant2];
    }
    for (int i = 0; i < defenceCards[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Defence:" + servant[servant2];
    }

    int servant3 = rnd.nextInt(this.servant.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてplayerCardsを埋める
    for (int i = 0; i < attackCards[servant3]; i++, pcNum++) {
      this.playerCards[pcNum] = "Attack:" + servant[servant3];
    }
    for (int i = 0; i < quickCards[servant3]; i++, pcNum++) {
      this.playerCards[pcNum] = "Quick:" + servant[servant3];
    }
    for (int i = 0; i < defenceCards[servant3]; i++, pcNum++) {
      this.playerCards[pcNum] = "Defence:" + servant[servant3];
    }
    for (int i = 0; i < this.playerCards.length; i++) {
      int r = rnd.nextInt(this.playerCards.length);
      String temp = this.playerCards[i];
      this.playerCards[i] = this.playerCards[r];
      this.playerCards[r] = temp;
    }
    System.out.println("Playerのサーヴァント召喚！");
    System.out.println(this.servant[servant1]);
    System.out.println(this.servant[servant2]);
    System.out.println(this.servant[servant3]);
    Thread.sleep(2000);

    // Create cpuCards
    // ランダムな数値を出してservantを選ぶ
    int servant4 = rnd.nextInt(this.servant.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてcpuCardsを埋める
    int cpuNum = 0;
    for (int i = 0; i < attackCards[servant4]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Attack:" + servant[servant4];
    }

    for (int i = 0; i < quickCards[servant4]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Quick:" + servant[servant4];
    }
    for (int i = 0; i < defenceCards[servant4]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Defence:" + servant[servant4];
    }

    int servant5 = rnd.nextInt(this.servant.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてcpuCardsを埋める
    for (int i = 0; i < attackCards[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Attack:" + servant[servant5];
    }
    for (int i = 0; i < quickCards[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Quick:" + servant[servant5];
    }
    for (int i = 0; i < defenceCards[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Defence:" + servant[servant5];
    }

    int servant6 = rnd.nextInt(this.servant.length);
    // 対応したservantのattack, quick, defenceの数にもとづいてcpuCardsを埋める
    for (int i = 0; i < attackCards[servant6]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Attack:" + servant[servant6];
    }
    for (int i = 0; i < quickCards[servant6]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Quick:" + servant[servant6];
    }
    for (int i = 0; i < defenceCards[servant6]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Defence:" + servant[servant6];
    }
    for (int i = 0; i < this.cpuCards.length; i++) {
      int r = rnd.nextInt(this.cpuCards.length);
      String temp = this.cpuCards[i];
      this.cpuCards[i] = this.cpuCards[r];
      this.cpuCards[r] = temp;
    }
    System.out.println("CPUのサーヴァント召喚！");
    System.out.println(this.servant[servant4]);
    System.out.println(this.servant[servant5]);
    System.out.println(this.servant[servant6]);
    Thread.sleep(2000);

  }

  public void initLibrary() {
    this.servant[0] = "シーザー";
    this.attackCards[0] = 3; // # of Attack Cards(シーザー)
    this.quickCards[0] = 1; // # of Quick Cards(シーザー)
    this.defenceCards[0] = 1; // # of Defence Cards(シーザー)

    this.servant[1] = "卑弥呼";
    this.attackCards[1] = 1;
    this.quickCards[1] = 3;
    this.defenceCards[1] = 1;

    this.servant[2] = "アテナ";
    this.attackCards[2] = 2;
    this.quickCards[2] = 2;
    this.defenceCards[2] = 1;

    this.servant[3] = "小野妹子";
    this.attackCards[3] = 1;
    this.quickCards[3] = 1;
    this.defenceCards[3] = 3;

    this.servant[4] = "シェイクスピア";
    this.attackCards[4] = 1;
    this.quickCards[4] = 2;
    this.defenceCards[4] = 2;

  }

  /**
   * @return the attackCards
   */
  public int[] getAttackCards() {
    return attackCards;
  }

  /**
   * @return the cpuAttackNum
   */
  public int getCpuAttackNum() {
    return cpuAttackNum;
  }

  /**
   * @return the cpuAttackPoint
   */
  public int getCpuAttackPoint() {
    return cpuAttackPoint;
  }

  /**
   * @return the cpuBattleCards
   */
  public String[] getCpuBattleCards() {
    return cpuBattleCards;
  }

  /**
   * @return the cpuBattlePoints
   */
  public int[] getCpuBattlePoints() {
    return cpuBattlePoints;
  }

  /**
   * @return the cpuCards
   */
  public String[] getCpuCards() {
    return cpuCards;
  }

  /**
   * @return the cpuDefenceNum
   */
  public int getCpuDefenceNum() {
    return cpuDefenceNum;
  }

  /**
   * @return the cpuGuardPoint
   */
  public int getCpuGuardPoint() {
    return cpuGuardPoint;
  }

  /**
   * @return the cpuHp
   */
  public int getCpuHp() {
    return cpuHp;
  }

  /**
   * @return the cpuQuickNum
   */
  public int getCpuQuickNum() {
    return cpuQuickNum;
  }

  /**
   * @return the cpuSpecialEffect
   */
  public String getCpuSpecialEffect() {
    return cpuSpecialEffect;
  }

  /**
   * @return the defenceCards
   */
  public int[] getDefenceCards() {
    return defenceCards;
  }

  /**
   * @return the playerAttackNum
   */
  public int getPlayerAttackNum() {
    return playerAttackNum;
  }

  /**
   * @return the playerAttackPoint
   */
  public int getPlayerAttackPoint() {
    return playerAttackPoint;
  }

  /**
   * @return the playerBattleCards
   */
  public String[] getPlayerBattleCards() {
    return playerBattleCards;
  }

  /**
   * @return the playerBattlePoints
   */
  public int[] getPlayerBattlePoints() {
    return playerBattlePoints;
  }

  /**
   * @return the playerCards
   */
  public String[] getPlayerCards() {
    return playerCards;
  }

  /**
   * @return the playerDefenceNum
   */
  public int getPlayerDefenceNum() {
    return playerDefenceNum;
  }

  /**
   * @return the playerGuardPoint
   */
  public int getPlayerGuardPoint() {
    return playerGuardPoint;
  }

  /**
   * @return the playerHp
   */
  public int getPlayerHp() {
    return playerHp;
  }

  /**
   * @return the playerQuickNum
   */
  public int getPlayerQuickNum() {
    return playerQuickNum;
  }

  /**
   * @return the playerSpecialEffect
   */
  public String getPlayerSpecialEffect() {
    return playerSpecialEffect;
  }

  /**
   * @return the quickCards
   */
  public int[] getQuickCards() {
    return quickCards;
  }

  /**
   * @return the servant
   */
  public String[] getServant() {
    return servant;
  }

  /**
   * @param attackCards the attackCards to set
   */
  public void setAttackCards(int[] attackCards) {
    this.attackCards = attackCards;
  }

  /**
   * @param cpuAttackNum the cpuAttackNum to set
   */
  public void setCpuAttackNum(int cpuAttackNum) {
    this.cpuAttackNum = cpuAttackNum;
  }

  /**
   * @param cpuAttackPoint the cpuAttackPoint to set
   */
  public void setCpuAttackPoint(int cpuAttackPoint) {
    this.cpuAttackPoint = cpuAttackPoint;
  }

  /**
   * @param cpuBattleCards the cpuBattleCards to set
   */
  public void setCpuBattleCards(String[] cpuBattleCards) {
    this.cpuBattleCards = cpuBattleCards;
  }

  /**
   * @param cpuBattlePoints the cpuBattlePoints to set
   */
  public void setCpuBattlePoints(int[] cpuBattlePoints) {
    this.cpuBattlePoints = cpuBattlePoints;
  }

  /**
   * @param cpuCards the cpuCards to set
   */
  public void setCpuCards(String[] cpuCards) {
    this.cpuCards = cpuCards;
  }

  /**
   * @param cpuDefenceNum the cpuDefenceNum to set
   */
  public void setCpuDefenceNum(int cpuDefenceNum) {
    this.cpuDefenceNum = cpuDefenceNum;
  }

  /**
   * @param cpuGuardPoint the cpuGuardPoint to set
   */
  public void setCpuGuardPoint(int cpuGuardPoint) {
    this.cpuGuardPoint = cpuGuardPoint;
  }

  /**
   * @param cpuHp the cpuHp to set
   */
  public void setCpuHp(int cpuHp) {
    this.cpuHp = cpuHp;
  }

  /**
   * @param cpuQuickNum the cpuQuickNum to set
   */
  public void setCpuQuickNum(int cpuQuickNum) {
    this.cpuQuickNum = cpuQuickNum;
  }

  /**
   * @param cpuSpecialEffect the cpuSpecialEffect to set
   */
  public void setCpuSpecialEffect(String cpuSpecialEffect) {
    this.cpuSpecialEffect = cpuSpecialEffect;
  }

  /**
   * @param defenceCards the defenceCards to set
   */
  public void setDefenceCards(int[] defenceCards) {
    this.defenceCards = defenceCards;
  }

  /**
   * @param playerAttackNum the playerAttackNum to set
   */
  public void setPlayerAttackNum(int playerAttackNum) {
    this.playerAttackNum = playerAttackNum;
  }

  /**
   * @param playerAttackPoint the playerAttackPoint to set
   */
  public void setPlayerAttackPoint(int playerAttackPoint) {
    this.playerAttackPoint = playerAttackPoint;
  }

  /**
   * @param playerBattleCards the playerBattleCards to set
   */
  public void setPlayerBattleCards(String[] playerBattleCards) {
    this.playerBattleCards = playerBattleCards;
  }

  /**
   * @param playerBattlePoints the playerBattlePoints to set
   */
  public void setPlayerBattlePoints(int[] playerBattlePoints) {
    this.playerBattlePoints = playerBattlePoints;
  }

  /**
   * @param playerCards the playerCards to set
   */
  public void setPlayerCards(String[] playerCards) {
    this.playerCards = playerCards;
  }

  /**
   * @param playerDefenceNum the playerDefenceNum to set
   */
  public void setPlayerDefenceNum(int playerDefenceNum) {
    this.playerDefenceNum = playerDefenceNum;
  }

  /**
   * @param playerGuardPoint the playerGuardPoint to set
   */
  public void setPlayerGuardPoint(int playerGuardPoint) {
    this.playerGuardPoint = playerGuardPoint;
  }

  /**
   * @param playerHp the playerHp to set
   */
  public void setPlayerHp(int playerHp) {
    this.playerHp = playerHp;
  }

  /**
   * @param playerQuickNum the playerQuickNum to set
   */
  public void setPlayerQuickNum(int playerQuickNum) {
    this.playerQuickNum = playerQuickNum;
  }

  /**
   * @param playerSpecialEffect the playerSpecialEffect to set
   */
  public void setPlayerSpecialEffect(String playerSpecialEffect) {
    this.playerSpecialEffect = playerSpecialEffect;
  }

  /**
   * @param quickCards the quickCards to set
   */
  public void setQuickCards(int[] quickCards) {
    this.quickCards = quickCards;
  }

  /**
   * @param servant the servant to set
   */
  public void setServant(String[] servant) {
    this.servant = servant;
  }
}

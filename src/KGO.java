import java.util.Random;

public class KGO {
  String[] s = new String[5];
  int[] ac = new int[5];
  int[] qc = new int[5];
  int[] dc = new int[5];

  int php = 100;
  String[] playerCards = new String[15];
  String[] pbc = new String[5];
  int[] pb = new int[5];
  int pa, pq, pd;
  int playerAttackPoint, playerGuardPoint;
  String playerSpecialEffect = "";

  int chp = 100;
  String[] cpuCards = new String[15];
  String[] cbc = new String[5];
  int[] cp = new int[5];
  int ca, cq, cd;
  int cpuAttackPoint, cpuGuardPoint;
  String cpuSpecialEffect = "";

  Random rnd = new Random();

  public void startPhase() throws InterruptedException {
    // Game Status初期化
    for (int i = 0; i < this.pbc.length; i++) {
      this.pbc[i] = null;
      this.pb[i] = 0;
    }
    this.pa = this.pq = this.pd = 0;
    this.playerSpecialEffect = "";

    for (int i = 0; i < this.cbc.length; i++) {
      this.cbc[i] = null;
      this.cp[i] = 0;
    }
    this.ca = this.cq = this.cd = 0;
    this.cpuSpecialEffect = "";

    System.out.println("PlayerのDraw--------------");
    // Draw Phase
    // playerCardsで文字列がDoneでないものを先頭から3枚取得し
    // pbc[5]に順に格納する
    // pb[5]に順に1~5のポイントをランダムに選択して格納する
    for (int i = 0, j = 0; j < 3; i++) {
      if (this.playerCards[i].equals("Done")) {
        continue;
      } else {
        this.pbc[j] = this.playerCards[i];
        this.pb[j] = this.rnd.nextInt(5) + 1;
        this.playerCards[i] = "Done";
        j++;
      }
    }

    for (int i = 0; i < this.pbc.length; i++) {
      if (this.pbc[i] != null) {
        System.out.println(this.pbc[i]);
      }
    }
    Thread.sleep(2000);

    // Attack,Quick,Defenceのカード枚数計測
    this.pa = 0;
    this.pq = 0;
    this.pd = 0;
    for (int i = 0; i < this.pbc.length; i++) {
      if (this.pbc[i] == null) {
        continue;
      } else if (this.pbc[i].contains("Attack")) {
        this.pa++;
      } else if (this.pbc[i].contains("Quick")) {
        this.pq++;
      } else if (this.pbc[i].contains("Defence")) {
        this.pd++;
      }
    }

    // カード特殊効果の処理(Quickのみ)
    // Quickの特殊効果は各Turn最初の1回のみ発動する
    if (this.pq >= 3) {// playerBattleCardsのうち2枚をランダムにコピーして追加
      System.out.println("Quick Chain3(Player) 手持ちのカードの中から2枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.pbc[3] = this.pbc[addCard1];
      this.pb[3] = this.pb[addCard1];
      int addCard2 = this.rnd.nextInt(3);
      this.pbc[4] = this.pbc[addCard2];
      this.pb[4] = this.pb[addCard2];
    } else if (this.pq >= 2) {// playerBattleCardsのうち2枚をコピーして追加
      System.out.println("Quick Chain2(Player) 手持ちのカードの中から1枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.pbc[3] = this.pbc[addCard1];
      this.pb[3] = this.pb[addCard1];
    }

    // Attack,Quick,Defenceのカード枚数計測再び
    this.pa = 0;
    this.pq = 0;
    this.pd = 0;
    for (int i = 0; i < this.pbc.length; i++) {
      if (this.pbc[i] == null) {
        continue;
      } else if (this.pbc[i].contains("Attack")) {
        this.pa++;
      } else if (this.pbc[i].contains("Quick")) {
        this.pq++;
      } else if (this.pbc[i].contains("Defence")) {
        this.pd++;
      }
    }

    // Attack,Guard Pointの計算
    // battlePointsのポイント合計をAttack, Guard両方に加算
    for (int i = 0; i < this.pb.length; i++) {
      this.playerAttackPoint = this.playerAttackPoint + this.pb[i];
      this.playerGuardPoint = this.playerGuardPoint + this.pb[i];
    }

    // カード特殊効果の処理(Attack)
    if (this.pa >= 3) {// 防御無視攻撃トリガーON
      System.out.println("PlayerのAttack Chain(3)! 防御無視攻撃！");
      this.playerSpecialEffect = "piercing"; // 防御無視
    } else if (this.pa >= 2) {// attack point2倍
      System.out.println("PlayerのAttack Chain(2)! Attack Point2倍！");
      this.playerAttackPoint = this.playerAttackPoint * 2;
    }

    // カード特殊効果の処理(Defence)
    if (this.pd >= 3) {// フルカウンタートリガーON
      System.out.println("PlayerのDefence Chain(3)! フルカウンターモード！");
      this.playerSpecialEffect = "counter";
    } else if (this.pd >= 2) {// guard point2倍
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
    // cbc[5]に順に格納する
    // cp[5]に順に1~5のポイントをランダムに選択して格納する
    for (int i = 0, j = 0; j < 3; i++) {
      if (this.cpuCards[i].equals("Done")) {
        continue;
      } else {
        this.cbc[j] = this.cpuCards[i];
        this.cp[j] = this.rnd.nextInt(5) + 1;
        this.cpuCards[i] = "Done";
        j++;
      }
    }

    for (int i = 0; i < this.cbc.length; i++) {
      if (this.cbc[i] != null) {
        System.out.println(this.cbc[i]);
      }
    }
    Thread.sleep(2000);

    // Attack,Quick,Defenceのカード枚数計測
    this.ca = 0;
    this.cq = 0;
    this.cd = 0;
    for (int i = 0; i < this.cbc.length; i++) {
      if (this.cbc[i] == null) {
        continue;
      } else if (this.cbc[i].contains("Attack")) {
        this.ca++;
      } else if (this.cbc[i].contains("Quick")) {
        this.cq++;
      } else if (this.cbc[i].contains("Defence")) {
        this.cd++;
      }
    }

    // カード特殊効果の処理(Quickのみ)
    // Quickの特殊効果は各Turn最初の1回のみ発動する
    if (this.cq >= 3) {// cpuBattleCardsのうち2枚をコピーして追加
      System.out.println("Quick Chain3(CPU) 手持ちのカードの中から2枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.cbc[3] = this.cbc[addCard1];
      this.cp[3] = this.cp[addCard1];
      int addCard2 = this.rnd.nextInt(3);
      this.cbc[4] = this.cbc[addCard2];
      this.cp[4] = this.cp[addCard2];
    } else if (this.cq >= 2) {// cpuBattleCardsのうち2枚をコピーして追加
      System.out.println("Quick Chain2(CPU) 手持ちのカードの中から1枚をコピーして追加！");
      int addCard1 = this.rnd.nextInt(3);
      this.cbc[3] = this.cbc[addCard1];
      this.cp[3] = this.cp[addCard1];
    }

    // Attack,Quick,Defenceのカード枚数計測再び
    this.ca = 0;
    this.cq = 0;
    this.cd = 0;
    for (int i = 0; i < this.cbc.length; i++) {
      if (this.cbc[i] == null) {
        continue;
      } else if (this.cbc[i].contains("Attack")) {
        this.ca++;
      } else if (this.cbc[i].contains("Quick")) {
        this.cq++;
      } else if (this.cbc[i].contains("Defence")) {
        this.cd++;
      }
    }

    // Attack,Guard Pointの計算
    // battlePointsのポイント合計をAttack, Guard両方に加算
    for (int i = 0; i < this.cp.length; i++) {
      this.cpuAttackPoint = this.cpuAttackPoint + this.cp[i];
      this.cpuGuardPoint = this.cpuGuardPoint + this.cp[i];
    }

    // カード特殊効果の処理(Attack)
    if (this.ca >= 3) {// 防御無視攻撃トリガーON
      System.out.println("CPUのAttack Chain(3)! 防御無視攻撃！");
      this.cpuSpecialEffect = "piercing"; // 防御無視
    } else if (this.ca >= 2) {// attack point2倍
      System.out.println("CPUのAttack Chain(2)! Attack Point2倍！");
      this.cpuAttackPoint = this.cpuAttackPoint * 2;
    }

    // カード特殊効果の処理(Defence)
    if (this.cd >= 3) {// フルカウンタートリガーON
      System.out.println("CPUのDefence Chain(3)! フルカウンターモード！");
      this.cpuSpecialEffect = "counter";
    } else if (this.cd >= 2) {// guard point2倍
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
          this.chp = this.chp - (this.playerAttackPoint - this.cpuGuardPoint);
        }
      } else if (this.cpuSpecialEffect.contains("counter")) {// CPUがcounterモードの場合，playerAttackPoint分のダメージをplayerHP(php)が受ける
        System.out.println("CPUのカウンター！");
        Thread.sleep(500);
        this.php = this.php - this.playerAttackPoint;
      }
    } else if (this.playerSpecialEffect.contains("piercing")) {// PlayerがpiercingモードでCPUがcounterモードでない場合，cpuのGuardPointを無視してcpuHp(chp)にダメージを与える
      System.out.println("Playerの防御無視攻撃！");
      Thread.sleep(500);
      if (this.cpuSpecialEffect.contains("counter")) {
        System.out.println("CPUのカウンター！");
        Thread.sleep(500);
        this.php = this.php - this.playerAttackPoint;
      } else {
        this.chp = this.chp - this.playerAttackPoint;
      }
    }

    // CPU Phase
    System.out.println("CPUの攻撃");
    Thread.sleep(500);
    if (this.cpuSpecialEffect.isEmpty() == true) {
      if (this.playerSpecialEffect.isEmpty() == true) {
        if (this.cpuAttackPoint - this.playerGuardPoint >= 0) {
          this.php = this.php - (this.cpuAttackPoint - this.playerGuardPoint);
        }
      } else if (this.playerSpecialEffect.contains("counter")) {
        System.out.println("Playerのカウンター！");
        Thread.sleep(500);
        this.chp = this.chp - this.cpuAttackPoint;
      }
    } else if (this.cpuSpecialEffect.contains("piercing")) {
      System.out.println("CPUの防御無視攻撃！");
      Thread.sleep(500);
      if (this.playerSpecialEffect.contains("counter")) {
        System.out.println("Playerのカウンター！");
        Thread.sleep(500);
        this.chp = this.chp - this.cpuAttackPoint;
      } else {
        this.php = this.php - this.cpuAttackPoint;
      }
    }
    System.out.println("Player HP:" + this.php);
    System.out.println("CPU HP:" + this.chp);

  }

  public void callServants() throws InterruptedException {

    // Create playerCards
    // ランダムな数値を出してservantを選ぶ
    int servant1 = rnd.nextInt(this.s.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてplayerCardsを埋める
    int pcNum = 0;
    for (int i = 0; i < ac[servant1]; i++, pcNum++) {
      this.playerCards[pcNum] = "Attack:" + s[servant1];
    }
    for (int i = 0; i < qc[servant1]; i++, pcNum++) {
      this.playerCards[pcNum] = "Quick:" + s[servant1];
    }
    for (int i = 0; i < dc[servant1]; i++, pcNum++) {
      this.playerCards[pcNum] = "Defence:" + s[servant1];
    }

    int servant2 = rnd.nextInt(this.s.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてplayerCardsを埋める
    for (int i = 0; i < ac[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Attack:" + s[servant2];
    }
    for (int i = 0; i < qc[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Quick:" + s[servant2];
    }
    for (int i = 0; i < dc[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Defence:" + s[servant2];
    }

    int servant3 = rnd.nextInt(this.s.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてplayerCardsを埋める
    for (int i = 0; i < ac[servant3]; i++, pcNum++) {
      this.playerCards[pcNum] = "Attack:" + s[servant3];
    }
    for (int i = 0; i < qc[servant3]; i++, pcNum++) {
      this.playerCards[pcNum] = "Quick:" + s[servant3];
    }
    for (int i = 0; i < dc[servant3]; i++, pcNum++) {
      this.playerCards[pcNum] = "Defence:" + s[servant3];
    }
    for (int i = 0; i < this.playerCards.length; i++) {
      int r = rnd.nextInt(this.playerCards.length);
      String temp = this.playerCards[i];
      this.playerCards[i] = this.playerCards[r];
      this.playerCards[r] = temp;
    }
    System.out.println("Playerのサーヴァント召喚！");
    System.out.println(this.s[servant1]);
    System.out.println(this.s[servant2]);
    System.out.println(this.s[servant3]);
    Thread.sleep(2000);

    // Create cpuCards
    // ランダムな数値を出してservantを選ぶ
    int servant4 = rnd.nextInt(this.s.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてcpuCardsを埋める
    int cpuNum = 0;
    for (int i = 0; i < ac[servant4]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Attack:" + s[servant4];
    }

    for (int i = 0; i < qc[servant4]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Quick:" + s[servant4];
    }
    for (int i = 0; i < dc[servant4]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Defence:" + s[servant4];
    }

    int servant5 = rnd.nextInt(this.s.length);
    // 対応したservantのattack, quick, defenceの数(合計5)にもとづいてcpuCardsを埋める
    for (int i = 0; i < ac[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Attack:" + s[servant5];
    }
    for (int i = 0; i < qc[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Quick:" + s[servant5];
    }
    for (int i = 0; i < dc[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Defence:" + s[servant5];
    }

    int servant6 = rnd.nextInt(this.s.length);
    // 対応したservantのattack, quick, defenceの数にもとづいてcpuCardsを埋める
    for (int i = 0; i < ac[servant6]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Attack:" + s[servant6];
    }
    for (int i = 0; i < qc[servant6]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Quick:" + s[servant6];
    }
    for (int i = 0; i < dc[servant6]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Defence:" + s[servant6];
    }
    for (int i = 0; i < this.cpuCards.length; i++) {
      int r = rnd.nextInt(this.cpuCards.length);
      String temp = this.cpuCards[i];
      this.cpuCards[i] = this.cpuCards[r];
      this.cpuCards[r] = temp;
    }
    System.out.println("CPUのサーヴァント召喚！");
    System.out.println(this.s[servant4]);
    System.out.println(this.s[servant5]);
    System.out.println(this.s[servant6]);
    Thread.sleep(2000);

  }

  public void initLibrary() {
    this.s[0] = "シーザー";
    this.ac[0] = 3; // # of Attack Cards(シーザー)
    this.qc[0] = 1; // # of Quick Cards(シーザー)
    this.dc[0] = 1; // # of Defence Cards(シーザー)

    this.s[1] = "卑弥呼";
    this.ac[1] = 1;
    this.qc[1] = 3;
    this.dc[1] = 1;

    this.s[2] = "アテナ";
    this.ac[2] = 2;
    this.qc[2] = 2;
    this.dc[2] = 1;

    this.s[3] = "小野妹子";
    this.ac[3] = 1;
    this.qc[3] = 1;
    this.dc[3] = 3;

    this.s[4] = "シェイクスピア";
    this.ac[4] = 1;
    this.qc[4] = 2;
    this.dc[4] = 2;

  }

  /**
   * @return the attackCards
   */
  public int[] getAttackCards() {
    return ac;
  }

  /**
   * @return the cpuAttackNum
   */
  public int getCpuAttackNum() {
    return ca;
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
    return cbc;
  }

  /**
   * @return the cpuBattlePoints
   */
  public int[] getCpuBattlePoints() {
    return cp;
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
    return cd;
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
    return chp;
  }

  /**
   * @return the cpuQuickNum
   */
  public int getCpuQuickNum() {
    return cq;
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
    return dc;
  }

  /**
   * @return the playerAttackNum
   */
  public int getPlayerAttackNum() {
    return pa;
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
    return pbc;
  }

  /**
   * @return the playerBattlePoints
   */
  public int[] getPlayerBattlePoints() {
    return pb;
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
    return pd;
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
    return php;
  }

  /**
   * @return the playerQuickNum
   */
  public int getPlayerQuickNum() {
    return pq;
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
    return qc;
  }

  /**
   * @return the servant
   */
  public String[] getServant() {
    return s;
  }

  /**
   * @param attackCards the attackCards to set
   */
  public void setAttackCards(int[] attackCards) {
    this.ac = attackCards;
  }

  /**
   * @param cpuAttackNum the cpuAttackNum to set
   */
  public void setCpuAttackNum(int cpuAttackNum) {
    this.ca = cpuAttackNum;
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
    this.cbc = cpuBattleCards;
  }

  /**
   * @param cpuBattlePoints the cpuBattlePoints to set
   */
  public void setCpuBattlePoints(int[] cpuBattlePoints) {
    this.cp = cpuBattlePoints;
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
    this.cd = cpuDefenceNum;
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
    this.chp = cpuHp;
  }

  /**
   * @param cpuQuickNum the cpuQuickNum to set
   */
  public void setCpuQuickNum(int cpuQuickNum) {
    this.cq = cpuQuickNum;
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
    this.dc = defenceCards;
  }

  /**
   * @param playerAttackNum the playerAttackNum to set
   */
  public void setPlayerAttackNum(int playerAttackNum) {
    this.pa = playerAttackNum;
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
    this.pbc = playerBattleCards;
  }

  /**
   * @param playerBattlePoints the playerBattlePoints to set
   */
  public void setPlayerBattlePoints(int[] playerBattlePoints) {
    this.pb = playerBattlePoints;
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
    this.pd = playerDefenceNum;
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
    this.php = playerHp;
  }

  /**
   * @param playerQuickNum the playerQuickNum to set
   */
  public void setPlayerQuickNum(int playerQuickNum) {
    this.pq = playerQuickNum;
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
    this.qc = quickCards;
  }

  /**
   * @param servant the servant to set
   */
  public void setServant(String[] servant) {
    this.s = servant;
  }
}

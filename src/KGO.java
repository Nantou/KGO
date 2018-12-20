import java.util.Random;

public class KGO {
  String[] servant = new String[5];
  int[] attackCards = new int[5];
  int[] quickCards = new int[5];
  int[] defenceCards = new int[5];
  String[] playerCards = new String[15];
  String[] cpuCards = new String[15];
  Random rnd = new Random();

  public void startPhase() {
    // Create playerCards
    // ランダムな数値を出してservantを選ぶ
    int servant1 = rnd.nextInt(5);
    // 対応したservantのattack, quick, defenceの数にもとづいてplayerCardsを埋める
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

    int servant2 = rnd.nextInt(5);
    // 対応したservantのattack, quick, defenceの数にもとづいてplayerCardsを埋める
    for (int i = 0; i < attackCards[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Attack:" + servant[servant2];
    }
    for (int i = 0; i < quickCards[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Quick:" + servant[servant2];
    }
    for (int i = 0; i < defenceCards[servant2]; i++, pcNum++) {
      this.playerCards[pcNum] = "Defence:" + servant[servant2];
    }

    int servant3 = rnd.nextInt(5);
    // 対応したservantのattack, quick, defenceの数にもとづいてplayerCardsを埋める
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

    // Create cpuCards
    // ランダムな数値を出してservantを選ぶ
    int servant4 = rnd.nextInt(5);
    // 対応したservantのattack, quick, defenceの数にもとづいてcpuCardsを埋める
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

    int servant5 = rnd.nextInt(5);
    // 対応したservantのattack, quick, defenceの数にもとづいてcpuCardsを埋める
    for (int i = 0; i < attackCards[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Attack:" + servant[servant5];
    }
    for (int i = 0; i < quickCards[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Quick:" + servant[servant5];
    }
    for (int i = 0; i < defenceCards[servant5]; i++, cpuNum++) {
      this.cpuCards[cpuNum] = "Defence:" + servant[servant5];
    }

    int servant6 = rnd.nextInt(5);
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

    System.out.println("Player----------------------");
    for (int i = 0; i < this.playerCards.length; i++) {
      System.out.println(this.playerCards[i]);
    }
    System.out.println("CPU-------------------------");
    for (String s : this.cpuCards) {
      System.out.println(s);
    }

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
}

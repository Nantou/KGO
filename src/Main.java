public class Main {
  public static void main(String[] args) throws InterruptedException {
    KGO kgame = new KGO();
    kgame.initLibrary();
    kgame.callServants();
    while (true) {
      kgame.startPhase();
      Thread.sleep(2000);

      // HPが0になったら勝敗が決定されて終了する
      if (kgame.getPlayerHp() <= 0 && kgame.getCpuHp() <= 0) {
        System.out.println("引き分け！");
      } else if (kgame.getPlayerHp() <= 0) {
        System.out.println("CPU Win!");
      } else if (kgame.getCpuHp() <= 0) {
        System.out.println("Player Win!");
      } else {
        // Deck(playerCards,cpuCards)のカードが無くなったらサーヴァント召喚
        int playerCardNum;
        for (playerCardNum = 0; playerCardNum < kgame.getPlayerCards().length; playerCardNum++) {
          if (!kgame.getPlayerCards()[playerCardNum].contains("Done")) {
            break;
          }
        }
        if (playerCardNum >= kgame.getPlayerCards().length) {
          kgame.callServants();
        }
        continue;
      }
      break;
    }

  }
}

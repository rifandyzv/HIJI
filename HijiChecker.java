public class HijiChecker extends Thread{
    public Player player;
    private Deck d;
    boolean hijiResult = false;
    public HijiChecker(Player player, Deck d){
        this.player = player;
        this.d = d;
    }

    public void run() {
          try {
            Thread.sleep(3000);
            HijiFailed();
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
      }

    public void HijiFailed(){
        System.out.println("Kamu lupa melakukan Declare HIJI!");
        System.out.println("Masih kangen main ya? Kamu dapat dua kartu tambahan!");
        player.drawCard(d);
        player.drawCard(d);
        this.hijiResult = true;
    }

    public boolean getHijiResult(){
        return hijiResult;
    }
}
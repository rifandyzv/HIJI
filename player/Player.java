package player;

public class Player {
    //Atributes
    public String name;
    static int playerNum = 0;
    // public boolean turn; kalo ada array buat urutan main ini gaperlu kayanya
    // private int jumlahKartu;
    // private List<Kartu> listKartu;


    //static
    {
        playerNum += 1;
    }

    //Constructor
    public Player(String name) {
        this.name = name;
    }

    //Method
    public static int getPlayerNum() {
        return playerNum;
    }
    // public int jmlKartu(){
    //     return jumlahKartu;
    // }

    // public void HIJI() {
    //     if (turn & (jumlahKartu == 1)) {
    //         System.out.println("HIIJII!!");
    //     }
    



}

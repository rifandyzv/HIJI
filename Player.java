import java.util.*;
public class Player {
    //Atributes
    public String name;
    static int playerNum = 0;
    // public boolean turn; kalo ada array buat urutan main ini gaperlu kayanya
    private int jumlahKartu;
    private ArrayList<Card> listKartu;


    //static
    {
        playerNum += 1;
    }

    //Constructor
    public Player(String name) {
        this.name = name;
        listKartu = new ArrayList<>();
        
    }

    //Method
    public static int getPlayerNum() {
        return playerNum;
    }
    public int getJmlKartu(){
        return jumlahKartu;
    }

    public void HIJI() {
        if (jumlahKartu == 1) {
            System.out.println("HIIJII!!");
        }
    }

    public void showCards() {
        for (int i = 0; i < listKartu.size(); i++){
            if(listKartu.get(i).type == Type.PLUS_2){

            }
        }
    }



}

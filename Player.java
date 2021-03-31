import java.util.*;
public class Player {
    //Atributes
    public String name;
    static int playerNum = 0;
    // public boolean turn; kalo ada array buat urutan main ini gaperlu kayanya
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
        return listKartu.size();
    }

    public void addPlayerCard(Card c){
        listKartu.add(c);
    }

    public void HIJI() {
        if (getJmlKartu() == 1) {
            System.out.println("HIIJII!!");
        }
    }

    public void showCards() {
        System.out.println("Kartu milik : " + name);
        for (int i = 0; i < listKartu.size(); i++){
            System.out.println((i+1) + ". " + listKartu.get(i).toString());
        }
    }

    public void drawCard(Deck d){
        addPlayerCard(d.getRandomCard());
    }

    public void discard(int index){
        listKartu.remove(index-1);
    }

    public Card getCard(int index){
        return listKartu.get(index);
    }

}

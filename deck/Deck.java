package deck;
import kartu.*;
import java.util.*;


public class Deck {
    //Atributes
    private static ArrayList<Kartu> listKartu;
    //constructor
    public Deck() {
        listKartu = new ArrayList<Kartu>(108);
        
    }
    //method
    public void addCard(int color, int value){
        Kartu k = new Kartu(color, value);
        listKartu.add(k);
    }

    public void createNumberCards(int color){
        for (int i = 0; i < 2; i++) {
            for (int value = 0; value < 10; value++){
                addCard(color, value);
            }
        }
    }

    public void createActionCards(int color){
        addCard(color, PLUS_2);


    }
    

    
}


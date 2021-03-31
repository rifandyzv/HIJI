import java.util.*;
public class Deck2 {
    //Atributes
    private static ArrayList<Card> listKartu;
    //constructor
    public Deck2() {
        listKartu = new ArrayList<Card>(108);
        
    }
    //method
    public void addCard(Card c){
        listKartu.add(c);
    }

    public void generateDeck() {
        createNumberCards(Warna.BIRU);
        createNumberCards(Warna.KUNING);
        createNumberCards(Warna.MERAH);
        createNumberCards(Warna.HIJAU);
        createActionCards(Warna.BIRU);
        createActionCards(Warna.KUNING);
        createActionCards(Warna.MERAH);
        createActionCards(Warna.HIJAU);
        createWildCards();        
    }

    public void createNumberCards(int color){
        Card nol = new angkaKartu(color, 0);
        addCard(nol);
        for (int i = 0; i < 2; i++) {
            for (int value = 1; value < 10; value++){
                Card tempCard = new angkaKartu(color, value);
                addCard(tempCard);
            }
        }
    }

    public void createActionCards(int color){
        for (int i = 0; i < 2; i++) {
            addCard(new DrawTwo(color));
            addCard(new Skip(color));
            addCard(new Reverse(color));
        }       
    }

    public void createWildCards(){
        for (int i = 0; i < 4; i++){
            addCard(new DrawFour());
            addCard(new Wild());
        }
    }
    

    
}


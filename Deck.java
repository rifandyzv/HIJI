import java.util.*;

public class Deck{
    List<Card> cardListDeck = new ArrayList<>();
    Random rand = new Random();

    public Deck(){
        createDeck();
    }

    public void createNumberCards(){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 10; j++){
                cardListDeck.add(new AngkaKartu(Warna.BIRU, j));
            }
            for(int j = 0; j < 10; j++){
                cardListDeck.add(new AngkaKartu(Warna.HIJAU, j));
            }
            for(int j = 0; j < 10; j++){
                cardListDeck.add(new AngkaKartu(Warna.MERAH, j));
            }
            for(int j = 0; j < 10; j++){
                cardListDeck.add(new AngkaKartu(Warna.KUNING, j));
            }
        }
    }

    public void createActionCards(){
        for(int i = 0; i < 2; i++){
            cardListDeck.add(new DrawTwo(Warna.BIRU));
            cardListDeck.add(new DrawTwo(Warna.HIJAU));
            cardListDeck.add(new DrawTwo(Warna.MERAH));
            cardListDeck.add(new DrawTwo(Warna.KUNING));
            cardListDeck.add(new Skip(Warna.BIRU));
            cardListDeck.add(new Skip(Warna.MERAH));
            cardListDeck.add(new Skip(Warna.HIJAU));
            cardListDeck.add(new Skip(Warna.KUNING));
            cardListDeck.add(new Reverse(Warna.BIRU));
            cardListDeck.add(new Reverse(Warna.HIJAU));
            cardListDeck.add(new Reverse(Warna.MERAH));
            cardListDeck.add(new Reverse(Warna.KUNING));
        }
    }

    public void createWildCards(){
        for(int i = 0; i < 4; i++){
            cardListDeck.add(new DrawFour());
            cardListDeck.add(new Wild());
        }
    }

    public void createDeck(){
        createNumberCards();
        createActionCards();
        createWildCards();
    }

    public Card getRandomCard(){
        int randomCard = rand.nextInt(cardListDeck.size() - 1);
        return cardListDeck.get(randomCard);
    }
}
import java.util.*;

public class Deck{
    List<Card> cardListDeck = new ArrayList<>();
    List<Card> cardStartDeck = new ArrayList<>();
    Random rand = new Random();

    public Deck(){
        createDeck();
        createStartingDeck();
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

    public void createStartingDeck(){
        for(int j = 0; j < 10; j++){
            cardStartDeck.add(new AngkaKartu(Warna.BIRU, j));
        }
        for(int j = 0; j < 10; j++){
            cardStartDeck.add(new AngkaKartu(Warna.HIJAU, j));
        }
        for(int j = 0; j < 10; j++){
            cardStartDeck.add(new AngkaKartu(Warna.MERAH, j));
        }
        for(int j = 0; j < 10; j++){
            cardStartDeck.add(new AngkaKartu(Warna.KUNING, j));
        }
    }
    public Card getRandomCard(){
        int randomCard = rand.nextInt(cardListDeck.size() - 1);
        return cardListDeck.get(randomCard);
    }

    public Card getStartCard(){
        int randomCard = rand.nextInt(cardStartDeck.size() - 1);
        return cardStartDeck.get(randomCard);
    }
}
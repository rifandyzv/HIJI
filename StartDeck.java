public class StartDeck extends Deck{
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

    public StartDeck(){
        createStartingDeck();
    }

    public Card getStartCard(){
        int randomCard = rand.nextInt(cardStartDeck.size() - 1);
        return cardStartDeck.get(randomCard);
    }

}
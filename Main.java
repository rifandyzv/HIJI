import java.util.*;

public class Main {
    //atributes
    private static List<Player> listPlayer;
    private static Deck deck = new Deck();
    private static boolean exit = false;
    private static boolean gameOn = true;
    
    //Method
    public void insertPlayer(int jumlahPemain){
        listPlayer = new ArrayList<>(jumlahPemain);
    }
    
    public void addPlayer(String name){
        Player p = new Player(name);
        bagiKartu(p);
        listPlayer.add(p);
    }
    
    public void showPlayers(){
        for(int i = 0; i < listPlayer.size(); i++){
            System.out.println("");
            System.out.println("Pemain " + (i+1) + " : " + listPlayer.get(i).name);
            System.out.println("Jumlah Kartu : " + listPlayer.get(i).getJmlKartu());
        }
    }

    public void bagiKartu(Player p){
        for (int i = 0; i < 7; i++){
            p.addPlayerCard(deck.getRandomCard());
        }
    }
    
    public void showPlayerInfo(int index){
        System.out.println("Pemain saat ini: " + listPlayer.get(index).name);
        System.out.println("Pemain selanjutnya: " + listPlayer.get((index+1) % listPlayer.size()).name);
    }

    public int getNextPlayerIndex(int currentPlayer){
        return (currentPlayer+1) % listPlayer.size();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner in = new Scanner(System.in);
        Help help = new Help();
        Card currentCard;
        boolean gameFinished = false;
        int currentPlayer = 0;
        // print menu
        System.out.println("##   ##   ##   # #    #    ##   ## ###### #    # #    #    #    #  #      #  # ");
        System.out.println("# # # #  #  #  # ##   #    # # # # #      ##   # #    #    #    #  #      #  # ");
        System.out.println("#  #  # #    # # # #  #    #  #  # #####  # #  # #    #    #    #  #      #  # ");
        System.out.println("#     # ###### # #  # #    #     # #      #  # # #    #    ######  #      #  # ");
        System.out.println("#     # #    # # #   ##    #     # #      #   ## #    #    #    #  #      #  # ");
        System.out.println("#     # #    # # #    #    #     # ###### #    #  ####     #    #  #  # #    # ");
        while (!exit) {
            
            System.out.println("Selamat datang di HIJI! Silahkan pilih perintah yang ingin dijalankan");
            System.out.println("1. Start Game");
            System.out.println("2. Exit");
            System.out.println("Masukkan perintah: ");
            int perintah = in.nextInt();
    
            if (perintah == 1){
                
                System.out.println("");
                System.out.println("Masukkan jumlah pemain: ");
                int jumlahPemain = in.nextInt();
                while(jumlahPemain > 6 || jumlahPemain < 2){
                    System.out.println("Jumlah pemain tidak memenuhi kriteria (antara 2 hingga 6 pemain)");
                    System.out.println("Masukkan kembali jumlah pemain");
                    jumlahPemain = in.nextInt();
                }
                main.insertPlayer(jumlahPemain); //set list array length
                for (int i = 0; i < jumlahPemain; i ++){
                        System.out.println("Masukan nama pemain " + (i+1) + " : ");
                        String namaPemain = in.next();
                        main.addPlayer(namaPemain);
                    }
                currentCard = deck.getStartCard(); 
                while(gameOn){
                    
                    System.out.println("");
                    System.out.println("Giliran : " + listPlayer.get(currentPlayer).name);
                    System.out.println("Kartu saat ini: " + currentCard.toString());
                    System.out.println("");
                    System.out.println("Masukkan perintah : ");
                    System.out.println("1. Show current players");
                    System.out.println("2. Show player cards");
                    System.out.println("3. Draw card");
                    System.out.println("4. View player in turn");
                    System.out.println("");
                    System.out.println("5. D I S C A R D !");
                    System.out.println("");
                    System.out.println("6. Help");
                    System.out.println("");
                    System.out.println("0. Exit");
                    int gameCommand = in.nextInt();
                    if (gameCommand == 0){
                        exit = true;
                        gameOn = false;
                    }
                    else if (gameCommand == 1) {
                        main.showPlayers();
                    }
                    else if (gameCommand == 2) {
                        listPlayer.get(currentPlayer).showCards();
                    }
                    else if (gameCommand == 3) {
                        listPlayer.get(currentPlayer).drawCard(deck);
                        currentPlayer = main.getNextPlayerIndex(currentPlayer);
                    }
                    else if (gameCommand == 4){
                        System.out.println("");
                        main.showPlayerInfo(currentPlayer);
                    }
                    else if (gameCommand == 5){
                        listPlayer.get(currentPlayer).showCards(); // nunjukin card juga biar ga ribet ngecek liat line liat line liat line
                        System.out.println("Masukkan nomor kartu yang ingin dimainkan: ");
                        int discardIndex = in.nextInt();
                        Card tempCard = listPlayer.get(currentPlayer).getCard(discardIndex-1);
                        int nextPlayer = main.getNextPlayerIndex(currentPlayer);
                        // if(tempCard.equalsColor(currentCard)){
                        if(tempCard.equals(currentCard)){
                            if(tempCard instanceof DrawTwo || tempCard instanceof Skip || tempCard instanceof Reverse){
                                if (tempCard instanceof DrawTwo){
                                    System.out.println("Hai " + listPlayer.get(main.getNextPlayerIndex(currentPlayer)).name + ", kamu dapat kado dua kartu!");
                                    System.out.println(nextPlayer);
                                    listPlayer.get(nextPlayer).drawCard(deck);
                                    listPlayer.get(nextPlayer).drawCard(deck); //entah knp satu kartu dikasih ke yang keluarin draw two, satu lagi ke orang tujuan
                                    currentPlayer = nextPlayer; //karena abis draw2 dia keskip
                                }
                                else if (tempCard instanceof Skip){
                                    System.out.println("Sampai jumpa " + listPlayer.get(nextPlayer).name + ", kamu tidak dapat giliran kali ini!");
                                    currentPlayer = nextPlayer;
                                }
                                else if(tempCard instanceof Reverse){
                                    Player tempPlayer = listPlayer.get(currentPlayer);
                                    Collections.reverse(listPlayer);
                                    for(int i = 0; i < listPlayer.size(); i++){ //Untuk mencari index pemain setelah reverse
                                        if(tempPlayer.equals(listPlayer.get(i))){
                                            currentPlayer = i;
                                        }
                                    }
                                }
                            }
                            currentCard = tempCard;
                            listPlayer.get(currentPlayer).discard(discardIndex);
                            currentPlayer = main.getNextPlayerIndex(currentPlayer); //kalau multiple discard harusnya belum ganti pemain
                        }
                        else if(tempCard instanceof Wild || tempCard instanceof DrawFour){
                            if (tempCard instanceof Wild){
                                System.out.println("Pilih warna :");
                                System.out.println("1. Hijau");
                                System.out.println("2. Kuning");
                                System.out.println("3. Merah");
                                System.out.println("4. Biru");
                                int wild = in.nextInt();
                                if (wild == Warna.MERAH) {
                                    tempCard.setColor(Warna.MERAH);
                                }
                                else if (wild == Warna.BIRU) {
                                    tempCard.setColor(Warna.BIRU);
                                }
                                else if (wild == Warna.KUNING) {
                                    tempCard.setColor(Warna.KUNING);
                                }
                                else if (wild == Warna.HIJAU) {
                                    tempCard.setColor(Warna.HIJAU);
                                }
                            } 
                            else if (tempCard instanceof DrawFour){
                                System.out.println("Halo " + listPlayer.get(main.getNextPlayerIndex(currentPlayer)).name + ", siap-siap ambil 4 kartu!");
                                System.out.println("");
                                System.out.println("Pilih warna :");
                                System.out.println("1. Hijau");
                                System.out.println("2. Kuning");
                                System.out.println("3. Merah");
                                System.out.println("4. Biru");
                                int wild = in.nextInt();
                                if (wild == Warna.MERAH) {
                                    tempCard.setColor(Warna.MERAH);
                                }
                                else if (wild == Warna.BIRU) {
                                    tempCard.setColor(Warna.BIRU);
                                }
                                else if (wild == Warna.KUNING) {
                                    tempCard.setColor(Warna.KUNING);
                                }
                                else if (wild == Warna.HIJAU) {
                                    tempCard.setColor(Warna.HIJAU);
                                }
                                listPlayer.get(nextPlayer).drawCard(deck);
                                listPlayer.get(nextPlayer).drawCard(deck);
                                listPlayer.get(nextPlayer).drawCard(deck);
                                listPlayer.get(nextPlayer).drawCard(deck);
                            }
                            currentCard = tempCard;
                            listPlayer.get(currentPlayer).discard(discardIndex);
                            if(listPlayer.get(currentPlayer).cardsInHand() == 0){
                                gameOn = false;
                                gameFinished = true;
                            }
                            currentPlayer = main.getNextPlayerIndex(currentPlayer);
                        }
                        // coba equalsnya sul
                        // yg elif bawah ini coba cmtin dulu
                    //     else if(currentCard.getAngka() == tempCard.getAngka()){
                    //         if(tempCard instanceof DrawTwo || tempCard instanceof Skip || tempCard instanceof Reverse){
                    //             if (tempCard instanceof DrawTwo){
                    //                 System.out.println("Hai " + listPlayer.get(main.getNextPlayerIndex(currentPlayer)).name + ", kamu dapat kado dua kartu!");
                    //                 System.out.println(nextPlayer);
                    //                 listPlayer.get(nextPlayer).drawCard(deck);
                    //                 listPlayer.get(nextPlayer).drawCard(deck); //entah knp satu kartu dikasih ke yang keluarin draw two, satu lagi ke orang tujuan
                    //                 currentPlayer = nextPlayer; //karena abis draw2 dia keskip
                    //             }
                    //             else if (tempCard instanceof Skip){
                    //                 System.out.println("Sampai jumpa " + listPlayer.get(nextPlayer).name + ", kamu tidak dapat giliran kali ini!");
                    //                 currentPlayer = nextPlayer;
                    //             }
                    //             else if(tempCard instanceof Reverse){
                    //                 Player tempPlayer = listPlayer.get(currentPlayer);
                    //                 Collections.reverse(listPlayer);
                    //                 for(int i = 0; i < listPlayer.size(); i++){ //Untuk mencari index pemain setelah reverse
                    //                     if(tempPlayer.equals(listPlayer.get(i))){
                    //                         currentPlayer = i;
                    //                     }
                    //                 }
                    //             }
                    //         }
                    //         currentCard = tempCard;
                    //         listPlayer.get(currentPlayer).discard(discardIndex);
                    //         currentPlayer = main.getNextPlayerIndex(currentPlayer); //kalau multiple discard harusnya belum ganti pemain
                    //     }
                    //     else{
                    //         System.out.println("Kartu tidak dapat dimainkan!");
                    //     }                        
                    }
                    // else if (gameCommand == 6){
                    //     System.out.println("");
                    //     help.printHelp();
                    // }
                }
            }
            else if (perintah == 2) {
                exit = true;
            }
            else {
                System.out.println("Masukkan perintah yang benar");
            }
        in.close();
        }
    }
}

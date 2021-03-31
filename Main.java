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
                main.insertPlayer(jumlahPemain); //set list array length
                for (int i = 0; i < jumlahPemain; i ++){
                        System.out.println("Masukan nama pemain " + (i+1) + " : ");
                        String namaPemain = in.next();
                        main.addPlayer(namaPemain);
                    }
                currentCard = deck.getRandomCard();
                while(gameOn){
                    //gameon disini
                    System.out.println("");
                    System.out.println("Masukkan perintah : ");
                    System.out.println("1. Show current players");
                    System.out.println("2. Show player cards");
                    System.out.println("3. Draw card");
                    System.out.println("4. View player in turn");
                    System.out.println("5. Discard");
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
                        listPlayer.get(0).showCards();
                    }
                    else if (gameCommand == 3) {
                        listPlayer.get(0).drawCard(deck);
                    }
                    else if (gameCommand == 4){
                        System.out.println("");
                        main.showPlayerInfo(currentPlayer);
                    }
                    else if (gameCommand == 5){
                        System.out.println("Masukkan nomor kartu yang ingin dimainkan: ");
                        int discardIndex = in.nextInt();
                        Card tempCard = listPlayer.get(currentPlayer).getCard(discardIndex);
                        if(tempCard instanceof Wild || tempCard instanceof DrawTwo || tempCard instanceof Skip || tempCard instanceof Reverse || tempCard instanceof DrawFour){
                            listPlayer.get(currentPlayer).discard(discardIndex);
                            if (tempCard instanceof Wild){
                                System.out.println("Pilih warna :");
                                
                            }
                            else if (tempCard instanceof DrawTwo){
                                System.out.println("Hai " + listPlayer.get(main.getNextPlayerIndex(currentPlayer)).name + ", kamu dapat kado dua kartu!");
                                listPlayer.get(main.getNextPlayerIndex(currentPlayer)).drawCard(deck);
                                listPlayer.get(main.getNextPlayerIndex(currentPlayer)).drawCard(deck);
                                currentPlayer = main.getNextPlayerIndex(currentPlayer);
                                currentPlayer = main.getNextPlayerIndex(currentPlayer);
                            }
                            else if (tempCard instanceof Skip){
                                System.out.println("Sampai jumpa " + listPlayer.get(main.getNextPlayerIndex(currentPlayer)).name + ", kamu tidak dapat giliran kali ini!");
                                currentPlayer = main.getNextPlayerIndex(currentPlayer);
                                currentPlayer = main.getNextPlayerIndex(currentPlayer);
                            }
                        }
                            //reverse belom draw4 blm
                        
                    }
                    else if (gameCommand == 6){
                        System.out.println("");
                        help.printHelp();
                    }
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

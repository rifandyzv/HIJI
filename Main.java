import java.util.*;

public class Main {
    //atributes
    private static List<Player> listPlayer;
    private static Deck deck = new Deck();
    private static StartDeck startDeck = new StartDeck();
    private static boolean exit = false;
    
    //Method
    public void insertPlayer(int jumlahPemain){
        listPlayer = new ArrayList<>(jumlahPemain);
    }
    
    public void addPlayer(String name){
        Player p = new Player(name);
        bagiKartu(p);
        listPlayer.add(p);
    }
    
    public void showPlayers(int currentPlayer){
        for(int i = 0; i < listPlayer.size(); i++){
            System.out.println("");
            System.out.println("Pemain " + (i+1) + " : " + listPlayer.get(i).name);
            System.out.println("Jumlah Kartu : " + listPlayer.get(i).getJmlKartu());
            if(i == currentPlayer){
                System.out.println("Sedang dalam giliran");
            }
            else{
                System.out.println("Tidak dalam giliran");
            }
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
        boolean gameOn = true;
        boolean gameFinished = false;
        int drawAccumulation = 0;
        int winnerIndex = -1;
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
            boolean onDraw = false;
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
                currentCard = startDeck.getStartCard(); 
                while(gameOn){
                    int skipTimes = 0;
                    // int drawTwoStatus = 0;
                    while (onDraw) {                        
                        System.out.println("Hai " + listPlayer.get(currentPlayer).name + ", siap siap ambil " + drawAccumulation + " kartu !");
                        System.out.println("");
                        System.out.println("1. Draw " + drawAccumulation + " kartu");
                        System.out.println("2. DISCARD !");
                        int draw = in.nextInt();
                        if (draw == 1) {
                            for (int i = 0; i < drawAccumulation; i++) {
                                listPlayer.get(currentPlayer).drawCard(deck);
                            }
                            drawAccumulation = 0;
                            currentPlayer = main.getNextPlayerIndex(currentPlayer);
                            onDraw = false;
                        }
                        else if (draw == 2) {
                            listPlayer.get(currentPlayer).showCards(); 
                            System.out.println("Masukkan nomor kartu yang ingin dimainkan: ");
                            int discardIndex = in.nextInt();
                            Card tempCard = listPlayer.get(currentPlayer).getCard(discardIndex-1);
                            int repetition = 1;
                            boolean multiCardCheck = false;
                            for(int i = 0; i < listPlayer.get(currentPlayer).cardsInHand(); i++){
                                if(i != (discardIndex-1)){
                                    if (tempCard.equalsStrict(listPlayer.get(currentPlayer).getCard(i))){
                                        multiCardCheck = true;
                                    }
                                }
                            }
                            if(multiCardCheck){
                                System.out.println("Apakah anda ingin melakukan Multiple Discard? (Mainkan lebih dari satu kartu yang sama PERSIS)");
                                System.out.println("1. Ya");
                                System.out.println("2. Tidak");
                                int multiDiscard = in.nextInt();
                                if(multiDiscard == 1){
                                    int multiDiscardIndex = -999;
                                    while(multiDiscardIndex != -1){
                                        listPlayer.get(currentPlayer).showCards();
                                        System.out.println(" ");
                                        System.out.println("-1: keluar dari Multiple Discard");
                                        System.out.println(" ");
                                        System.out.println("Masukkan nomor kartu yang ingin disertakan di Multiple Discard:");
                                        multiDiscardIndex = in.nextInt();
                                        if(multiDiscardIndex != -1){
                                            if(multiDiscardIndex == discardIndex){
                                                System.out.println("Kartu tersebut sudah dipilih!");
                                            }
                                            else if(tempCard.equalsStrict(listPlayer.get(currentPlayer).getCard(multiDiscardIndex-1))){ 
                                                if(multiDiscardIndex > discardIndex){
                                                    listPlayer.get(currentPlayer).discard(multiDiscardIndex);
                                                    repetition += 1;
                                                    System.out.println("Kartu berhasil dibuang");
                                                }
                                                else if(multiDiscardIndex < discardIndex){
                                                    listPlayer.get(currentPlayer).discard(discardIndex);
                                                    discardIndex = multiDiscardIndex;
                                                    repetition += 1;
                                                    System.out.println("Kartu berhasil dibuang");
                                                }
                                            }
                                            else{
                                                System.out.println("Kartu tidak sama persis!");
                                            }
                                        }
                                    }
                                }
                            }
                            if (tempCard instanceof DrawFour && currentCard instanceof DrawFour){
                                for(int i = 0; i < repetition; i++){
                                    drawAccumulation += 4;
                                }
                                System.out.println("");
                                System.out.println("Pilih warna :");
                                System.out.println("1. Hijau");
                                System.out.println("2. Kuning");
                                System.out.println("3. Merah");
                                System.out.println("4. Biru");
                                int wild = in.nextInt();
                                if (wild == Warna.MERAH) {
                                    tempCard = new DrawFour(Warna.MERAH);
                                }
                                else if (wild == Warna.BIRU) {
                                    tempCard = new DrawFour(Warna.BIRU);
                                }
                                else if (wild == Warna.KUNING) {
                                    tempCard = new DrawFour(Warna.KUNING);
                                }
                                else if (wild == Warna.HIJAU) {
                                    tempCard = new DrawFour(Warna.HIJAU);
                                }
                                currentCard = tempCard;
                                listPlayer.get(currentPlayer).discard(discardIndex);
                            }
                            else if (tempCard instanceof DrawTwo && currentCard instanceof DrawTwo) {
                                for (int i = 0; i < repetition; i++) {
                                    drawAccumulation += 2;
                                }
                                currentCard = tempCard;
                                listPlayer.get(currentPlayer).discard(discardIndex);
                            }
                            else {
                                System.out.println("Kartu tidak sesuai !");
                                System.out.println("Ambil " + drawAccumulation + " kartu !!!");
                                for (int i = 0; i < drawAccumulation; i++) {
                                    listPlayer.get(currentPlayer).drawCard(deck);
                                }
                                onDraw = false;
                            }
                            
                            currentPlayer = main.getNextPlayerIndex(currentPlayer);
                            
                        }
                            
                        
                    }
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
                    System.out.println("6. HIJI!");
                    System.out.println("");
                    System.out.println("7. Help");
                    System.out.println("");
                    System.out.println("0. Exit");
                    

                    int gameCommand = in.nextInt();
                    if (gameCommand == 0){
                        exit = true;
                        gameOn = false;
                    }
                    else if (gameCommand == 1) {
                        main.showPlayers(currentPlayer);
                    }
                    else if (gameCommand == 2) {
                        listPlayer.get(currentPlayer).showCards();
                    }
                    else if (gameCommand == 3) {
                        Card tempDraw = deck.getRandomCard();
                        System.out.println("Kartu yang anda dapatkan adalah: " + tempDraw.toString());
                        listPlayer.get(currentPlayer).addPlayerCard(tempDraw);
                        // Hanya bisa memilih jika kartu yg di-draw equal
                        // Kalau tidak equals lanjut ke nextplayer
                        if (tempDraw.equals(currentCard) || tempDraw instanceof Wild || tempDraw instanceof DrawFour){
                            System.out.println("Apakah anda ingin memainkan kartu ini?");
                            System.out.println("1. Ya");
                            System.out.println("2. Tidak");
                            int drawCommand = in.nextInt();
                            if(drawCommand == 2){
                                currentPlayer = main.getNextPlayerIndex(currentPlayer);
                            }
                            else if(drawCommand == 1){
                                System.out.println("Silahkan main lagi dan gunakan kartu yang anda baru dapatkan!");
                            }
                        }
                        else{
                            currentPlayer = main.getNextPlayerIndex(currentPlayer);
                        }
                    }
                    else if (gameCommand == 4){
                        System.out.println("");
                        main.showPlayerInfo(currentPlayer);
                    }
                    else if (gameCommand == 5){
                        listPlayer.get(currentPlayer).showCards();
                        System.out.println("");
                        System.out.println("Input -1 untuk kembali");
                        System.out.println(""); 
                        System.out.println("Masukkan nomor kartu yang ingin dimainkan: ");
                        int discardIndex = in.nextInt();
                        if(discardIndex <= listPlayer.get(currentPlayer).cardsInHand() && discardIndex > 0){                      
                            Card tempCard = listPlayer.get(currentPlayer).getCard(discardIndex-1);
                            int nextPlayer = main.getNextPlayerIndex(currentPlayer);
                            if(tempCard.equals(currentCard)){
                                int repetition = 1;
                                boolean multiCardCheck = false;
                                for(int i = 0; i < listPlayer.get(currentPlayer).cardsInHand(); i++){
                                    if(i != (discardIndex-1)){
                                        if (tempCard.equalsStrict(listPlayer.get(currentPlayer).getCard(i))){
                                            multiCardCheck = true;
                                        }
                                    }
                                }
                                if(multiCardCheck){
                                    System.out.println("Apakah anda ingin melakukan Multiple Discard? (Mainkan lebih dari satu kartu yang sama PERSIS)");
                                    System.out.println("1. Ya");
                                    System.out.println("2. Tidak");
                                    int multiDiscard = in.nextInt();
                                    if(multiDiscard == 1){
                                        int multiDiscardIndex = -999;
                                        while(multiDiscardIndex != -1){
                                            listPlayer.get(currentPlayer).showCards();
                                            System.out.println(" ");
                                            System.out.println("-1: keluar dari Multiple Discard");
                                            System.out.println(" ");
                                            System.out.println("Masukkan nomor kartu yang ingin disertakan di Multiple Discard:");
                                            multiDiscardIndex = in.nextInt();
                                            if(multiDiscardIndex != -1){
                                                if(multiDiscardIndex == discardIndex){
                                                    System.out.println("Kartu tersebut sudah dipilih!");
                                                }
                                                else if(tempCard.equalsStrict(listPlayer.get(currentPlayer).getCard(multiDiscardIndex-1))){ 
                                                    if(multiDiscardIndex > discardIndex){
                                                        listPlayer.get(currentPlayer).discard(multiDiscardIndex);
                                                        repetition += 1;
                                                        System.out.println("Kartu berhasil dibuang");
                                                    }
                                                    else if(multiDiscardIndex < discardIndex){
                                                        listPlayer.get(currentPlayer).discard(discardIndex);
                                                        discardIndex = multiDiscardIndex;
                                                        repetition += 1;
                                                        System.out.println("Kartu berhasil dibuang");
                                                    }
                                                }
                                                else{
                                                    System.out.println("Kartu tidak sama persis!");
                                                }
                                            }
                                        }
                                    }
                                }
                                for(int i = 0; i < repetition; i++){
                                    if(tempCard instanceof DrawTwo || tempCard instanceof Skip || tempCard instanceof Reverse){
                                        if (tempCard instanceof DrawTwo){
                                            // System.out.println("Hai " + listPlayer.get(main.getNextPlayerIndex(currentPlayer)).name + ", kamu dapat kado dua kartu!");
                                            drawAccumulation += 2;
                                            // drawTwoStatus = 1;
                                            onDraw = true;
                                        }
                                        else if (tempCard instanceof Skip){
                                            System.out.println("Sampai jumpa " + listPlayer.get(nextPlayer).name + ", kamu tidak dapat giliran kali ini!");
                                            skipTimes += 1;
                                        }
                                        else if(tempCard instanceof Reverse){
                                            Player tempPlayer = listPlayer.get(currentPlayer);
                                            Collections.reverse(listPlayer);
                                            for(int j = 0; j < listPlayer.size(); j++){ //Untuk mencari index pemain setelah reverse
                                                if(tempPlayer.equals(listPlayer.get(j))){
                                                    currentPlayer = j;
                                                }
                                            }
                                        }
                                    }
                                }
                                currentCard = tempCard;
                                listPlayer.get(currentPlayer).discard(discardIndex);
                                if(listPlayer.get(currentPlayer).cardsInHand() == 0){
                                    gameOn = false;
                                    gameFinished = true;
                                    winnerIndex = currentPlayer;
                                }
                                else if(listPlayer.get(currentPlayer).cardsInHand() == 1){
                                    boolean hijiCheck = false;
                                    HijiChecker hiji = new HijiChecker(listPlayer.get(currentPlayer), deck);
                                    hiji.start();
                                    while(hijiCheck == false){
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
                                        System.out.println("6. HIJI!");
                                        System.out.println("");
                                        System.out.println("7. Help");
                                        System.out.println("");
                                        System.out.println("0. Exit");
                                        gameCommand = in.nextInt();
                                        hijiCheck = hiji.getHijiResult();
                                        if(gameCommand == 6 && (!hijiCheck)){
                                            System.out.println(listPlayer.get(currentPlayer).name + " : 'HIJIII!'");
                                            System.out.println("Hati-hati para pemain, " + listPlayer.get(currentPlayer).name + " hanya punya satu kartu lagi!");
                                            hiji.interrupt();
                                            hijiCheck = true;
                                        }
                                    }
                                }
                                // if(drawTwoStatus == 1){
                                //     skipTimes += 1;
                                // }
                                currentPlayer = main.getNextPlayerIndex(currentPlayer); 
                                for(int i = 0; i < skipTimes; i++){
                                    currentPlayer = main.getNextPlayerIndex(currentPlayer);
                                }
                            }
                            else if(tempCard instanceof Wild || tempCard instanceof DrawFour){
                                int repetition = 1;
                                boolean multiCardCheck = false;
                                for(int i = 0; i < listPlayer.get(currentPlayer).cardsInHand(); i++){
                                    if(i != (discardIndex-1)){
                                        if (tempCard.equalsStrict(listPlayer.get(currentPlayer).getCard(i))){
                                            multiCardCheck = true;
                                        }
                                    }
                                }
                                if(multiCardCheck){
                                    System.out.println("Apakah anda ingin melakukan Multiple Discard? (Mainkan lebih dari satu kartu yang sama PERSIS)");
                                    System.out.println("1. Ya");
                                    System.out.println("2. Tidak");
                                    int multiDiscard = in.nextInt();
                                    if(multiDiscard == 1){
                                        int multiDiscardIndex = -999;
                                        while(multiDiscardIndex != -1){
                                            listPlayer.get(currentPlayer).showCards();
                                            System.out.println(" ");
                                            System.out.println("-1: keluar dari Multiple Discard");
                                            System.out.println(" ");
                                            System.out.println("Masukkan nomor kartu yang ingin disertakan di Multiple Discard:");
                                            multiDiscardIndex = in.nextInt();
                                            if(multiDiscardIndex != -1){
                                                if(multiDiscardIndex == discardIndex){
                                                    System.out.println("Kartu tersebut sudah dipilih!");
                                                }
                                                else if(tempCard.equalsStrict(listPlayer.get(currentPlayer).getCard(multiDiscardIndex-1))){ 
                                                    if(multiDiscardIndex > discardIndex){
                                                        listPlayer.get(currentPlayer).discard(multiDiscardIndex);
                                                        repetition += 1;
                                                        System.out.println("Kartu berhasil dibuang");
                                                    }
                                                    else if(multiDiscardIndex < discardIndex){
                                                        listPlayer.get(currentPlayer).discard(discardIndex);
                                                        discardIndex = multiDiscardIndex;
                                                        repetition += 1;
                                                        System.out.println("Kartu berhasil dibuang");
                                                    }
                                                }
                                                else{
                                                    System.out.println("Kartu tidak sama persis!");
                                                }
                                            }
                                        }
                                    }
                                }
                                if (tempCard instanceof Wild){
                                    System.out.println("Pilih warna :");
                                    System.out.println("1. Hijau");
                                    System.out.println("2. Kuning");
                                    System.out.println("3. Merah");
                                    System.out.println("4. Biru");
                                    int wild = in.nextInt();
                                    if (wild == Warna.MERAH) {
                                        tempCard = new Wild(Warna.MERAH);
                                    }
                                    else if (wild == Warna.BIRU) {
                                        tempCard = new Wild(Warna.BIRU);
                                    }
                                    else if (wild == Warna.KUNING) {
                                        tempCard = new Wild(Warna.KUNING);
                                    }
                                    else if (wild == Warna.HIJAU) {
                                        tempCard = new Wild(Warna.HIJAU);
                                    }
                                } 
                                else if (tempCard instanceof DrawFour){
                                    // System.out.println("Halo " + listPlayer.get(main.getNextPlayerIndex(currentPlayer)).name + ", siap-siap ambil " + (repetition*4) + " kartu!");
                                    System.out.println("");
                                    System.out.println("Pilih warna :");
                                    System.out.println("1. Hijau");
                                    System.out.println("2. Kuning");
                                    System.out.println("3. Merah");
                                    System.out.println("4. Biru");
                                    int wild = in.nextInt();
                                    if (wild == Warna.MERAH) {
                                        tempCard = new DrawFour(Warna.MERAH);
                                    }
                                    else if (wild == Warna.BIRU) {
                                        tempCard = new DrawFour(Warna.BIRU);
                                    }
                                    else if (wild == Warna.KUNING) {
                                        tempCard = new DrawFour(Warna.KUNING);
                                    }
                                    else if (wild == Warna.HIJAU) {
                                        tempCard = new DrawFour(Warna.HIJAU);
                                    }
                                    for(int j = 0; j < repetition; j++){
                                        drawAccumulation += 4;
                                    }

                                    onDraw = true;
                                }
                                currentCard = tempCard;
                                listPlayer.get(currentPlayer).discard(discardIndex);
                                if(listPlayer.get(currentPlayer).cardsInHand() == 0){
                                    gameOn = false;
                                    gameFinished = true;
                                    winnerIndex = currentPlayer;
                                }
                                else if(listPlayer.get(currentPlayer).cardsInHand() == 1){
                                    boolean hijiCheck = false;
                                    HijiChecker hiji = new HijiChecker(listPlayer.get(currentPlayer), deck);
                                    hiji.start();
                                    while(hijiCheck == false){
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
                                        System.out.println("6. HIJI!");
                                        System.out.println("");
                                        System.out.println("7. Help");
                                        System.out.println("");
                                        System.out.println("0. Exit");
                                        gameCommand = in.nextInt();
                                        hijiCheck = hiji.getHijiResult();
                                        if(gameCommand == 6 && (!hijiCheck)){
                                            System.out.println("HIJIII!");
                                            System.out.println("Hati-hati para pemain, " + listPlayer.get(currentPlayer).name + " hanya punya satu kartu lagi!");
                                            System.err.println("");
                                            hiji.interrupt();
                                            hijiCheck = true;
                                        }
                                    }
                                }
                                currentPlayer = main.getNextPlayerIndex(currentPlayer);
                            }
                            
                            else{
                                System.out.println("Kartu tidak dapat dimainkan!");
                            }
                        }
                        else if (discardIndex == -1) {

                        }
                        else {
                            System.out.println("Tidak ada kartu !");
                        }
                    }
                    else if(gameCommand == 6){
                        if(listPlayer.get(currentPlayer).cardsInHand() != 1){
                            System.out.println("Pelan-pelan gan, kartunya masih banyak!");
                            System.out.println("Ambil 2 kartu lagi deh karena semangat banget hehe");
                            listPlayer.get(currentPlayer).drawCard(deck); 
                            listPlayer.get(currentPlayer).drawCard(deck); 
                        }
                    }
                    else if(gameCommand == 7){
                        help.printHelp();
                    }



                    
                }
                if(gameFinished){
                    System.out.println("");
                    System.out.println("Permainan selesai!");
                    System.out.println("Selamat kepada " + listPlayer.get(winnerIndex).name + "yang telah memenangkan permainan HIJI kali ini!");
                    System.out.println("Terima kasih telah bermain, sampai jumpa!");
                    exit = true;
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

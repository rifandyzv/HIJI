public abstract class Kartu {
    //warna
    public static final int HIJAU = 15 ;
    public static final int KUNING = 16;
    public static final int MERAH = 17;
    public static final int BIRU = 18;
    public static final int BLACK = 19;
    //values
    public static final int NOL = 0;
    public static final int SATU = 1;
    public static final int DUA = 2;
    public static final int TIGA = 3;
    public static final int EMPAT = 4;
    public static final int LIMA = 5;
    public static final int ENAM = 6;
    public static final int TUJUH = 7;
    public static final int DELAPAN = 8;
    public static final int SEMBILAN = 9;
    public static final int PLUS_2 = 10;
    public static final int PLUS_4 = 11;
    public static final int SKIP = 12;
    public static final int REVERSE = 13;
    public static final int WILD = 14;
    
    //atributes
    private int color;
    private int value;

    //cons
    public Kartu(int color, int value){
        this.color = color;
        this.value = value;
        int cek = SATU;
    }


}

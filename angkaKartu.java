public class angkaKartu extends Card {
    private int angka;
    private int type;
    public angkaKartu(int c, int angka){ 
        super(c);
        this.angka = angka;
    }
    public int getAngka(){
        return this.angka;
    }
}

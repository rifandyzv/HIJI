public class AngkaKartu extends Card {
    private int angka;
    private static int type = Type.NUMBER;
    public AngkaKartu(int c, int angka){ 
        super(c);
        this.angka = angka;
    }
    public String getAngka(){
        Integer n = angka;
        return n.toString();   
    }

    public String getType(){
        return "Number Card";
    }


}

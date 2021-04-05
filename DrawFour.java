public class DrawFour extends Card{
    //atributes
    private static int type = Type.PLUS_4;
    private static int takecard = 4;
    //constructor
    public DrawFour(){
        super(Warna.BLACK);   
    }
    public DrawFour(int c){
        super(c);   
    }

    public String getAngka(){
        return "PLUS 4";
    }
    public String getType(){
        return "PLUS 4";
    }
    public int getTakeCard(){
        return takecard;
    }

}

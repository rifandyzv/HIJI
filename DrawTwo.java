public class DrawTwo extends Card{
    private static int takecard = 2;
    private static int type = Type.PLUS_2;
    public DrawTwo(int c){
        super(c);
    }

    public String getAngka(){
        return "PLUS 2";
    }
    public String getType(){
        return "PLUS 2";
    }
    public int getTakeCard(){
        return takecard;
    }
}
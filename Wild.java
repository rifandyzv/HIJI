public class Wild extends Card{
    private static int type = Type.WILD;

    public Wild(){
        super(Warna.BLACK);
    }

    //method
    public String getAngka(){
        return "Wild";
    }
    public String getType(){
        return "Wild";
    }
}

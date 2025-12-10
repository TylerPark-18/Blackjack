
public class Card{
    //H, D, C, S
    //1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
    
    private int value;
    private String suit;

    
    private String path= "card_images/";
    public Card(int value, String suit){
        this.suit = suit;
        this.value= value;
        path+="1";
    }
    public int getValue(){
        return value;
    }
    public String getSuit(){
        return suit;
    }
    public String getPath(){
        return path;
    }
    
}
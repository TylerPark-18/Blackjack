
public class Card{
    //H, D, C, S
    //1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
    
    private int value;
    private String suit;

    

    public Card(int value, String suit){
        this.suit = suit;
        this.value= value;
    }
    public int getValue(){
        return value;
    }
    public String getSuit(){
        return suit;
    }
    
}
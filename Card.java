
public class Card{
    //H, D, C, S
    //1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
    
    private int value;
    private String suit;

    
    private String path = "card_images/";
    private String imageFile = "";

    public Card(int value, String suit){
        this.suit = suit;
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    public String getSuit(){
        return suit;
    }
    public String getPath(){
        if (imageFile == null || imageFile.isEmpty())
            return path;
        return path + imageFile;
    }

    public void setImage(String imageFile){
        this.imageFile = imageFile;
    }

    public void setImageIndex(int index) {
        this.imageFile = index + ".jpg";
    }
}
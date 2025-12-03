import java.util.ArrayList;
import javax.swing.*;

public class Blackjack extends JPanel{
    
    ArrayList<Card> cards = new ArrayList<>();
    
    public void Blackjack(){
        initializeDeck();
    }

    public void initializeDeck(){
        String[] difSuits = {"Diamonds","Clubs","Hearts","Spades"};
        for(String Suits : difSuits){
            for(int i = 1; i <= 13; i++)
                cards.add(new Card(i,Suits));
        }
        System.out.println(cards);
    }

    //Array list with all cards
    //Pull a random card and remove that card from the deck
    //Calculate winner and scores
    //J-Frame

}
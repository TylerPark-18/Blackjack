import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Blackjack extends JPanel{

    private int numCards = 52;
    private int playerPoints = 0;
    private int dealerPoints = 0;
    
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Card> usedCards = new ArrayList<>();

    private JFrame myFrame;
    Scanner scanner = new Scanner(System.in);

    
    public Blackjack(){
        initializeDeck();

        //setup
        playerDraws();
        dealerDraws();
        playerDraws();
        dealerDraws();

        while(playerPoints < 22 || dealerPoints < 22){
            askPlayer();
        }
       


    }

    public void initializeDeck(){
        String[] difSuits = {"Diamonds","Clubs","Hearts","Spades"};
        for(String Suits : difSuits){
            for(int i = 1; i <= 13; i++)
                cards.add(new Card(i,Suits));
        }
        
    }

   public Card drawCard() {
        int r = (int)(Math.random() * numCards);
        numCards--;
        Card c = cards.get(r);
        usedCards.add(c);
        cards.remove(r);
        return c; 
   }

   public void playerDraws(){
        Card players = drawCard();
        int val = players.getValue();
        playerPoints += val;
        System.out.println("value: " + val);
        System.out.println("Players points: " + playerPoints);
   }

   public void dealerDraws(){
        Card dealers = drawCard();
        int val = dealers.getValue();
        System.out.println("value: "+ val);
        dealerPoints += val;
        System.out.println("Dealers points: " + dealerPoints);
        
   }
   public void askPlayer(){
        System.out.println("Hit or Stay?");
        String HitorStay = scanner.nextLine();
        System.out.println("You "+ HitorStay);
   }
   public void paintComponent(Graphics g) {
      super.repaint();

    //Array list with all cards
    //Pull a random card and remove that card from the deck
    //Calculate winner and scores
    //J-Frame
   }
}

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;



public class Blackjack extends JPanel{
    Blackjack1 blackjack;
    private int numCards = 52;
    private int playerPoints = 0;
    private int dealerPoints = 0;
    private boolean playerStays = false;
    private boolean dealerStays = false;
    private boolean playerBusted = false;
    private boolean dealerBusted = false;
    private int windowHeight = 500;
    private int windowWidth = 400;
    private JTextPane textArea;
    
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Card> usedCards = new ArrayList<>();

    private JFrame myFrame;
    static Scanner scanner = new Scanner(System.in);

    
    public Blackjack(){
        initializeDeck();
        this.setOpaque(false);
        blackjack = new Blackjack1();
        
        //blackjack.getContentPane().add(this, 0);
        blackjack.setSize(windowWidth, windowHeight);
        blackjack.setVisible(true);
        
        
        
        //setup
        playerDraws();
        dealerDraws();
        playerDraws();
        dealerDraws();
        
        blackjack.setMessage("Players points: " + playerPoints);
        blackjack.setMessage("Dealers points: " + dealerPoints);
        
        //draws
        while(playerPoints < 22 && !playerStays) {
            response();
            System.out.println("Player's points: " + playerPoints);
        }
        
        while(dealerPoints < 16){
            System.out.println("Dealer draws");
            dealerDraws();
        }
        
        //tally
        System.out.println("");
        System.out.println("Results: ");
        System.out.println("Player has " + playerPoints+ " points");
        System.out.println("Dealer has " + dealerPoints + " points");
        
        //winner: 
        results();
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
        if(val > 10)
            val = 10;
        playerPoints += val;
        
   }

   public void dealerDraws(){
        Card dealers = drawCard();
        int val = dealers.getValue();
        if(val > 10)
            val = 10;
        dealerPoints += val;

   }

   public static String askPlayer(){
        System.out.println("Hit or Stay?");
        return scanner.nextLine(); 
   }

//what the player wants to do..
   public void response(){
    if (askPlayer().equals("Stay"))
        playerStays = true;
    else 
        
        playerDraws();
   }
   public void results(){
        if (playerPoints > dealerPoints && playerPoints < 22){
            System.out.println("You win");
        } else if (dealerPoints > playerPoints && dealerPoints < 22){
            System.out.println("Dealer wins");
        } else if (dealerPoints < playerPoints && playerPoints> 21){
            System.out.println("You busted");
        } else if (playerPoints< dealerPoints && dealerPoints > 21){
            System.out.println("You win");
        } else {
            System.out.println("You tied");
            new Blackjack();
        } 
   }
}



    //Array list with all cards
    //Pull a random card and remove that card from the deck
    //Calculate winner and scores
    //J-Frame

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private String path= "card_images/";
    private boolean aceDrawn;
    
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Card> usedCards = new ArrayList<>();

    private JFrame myFrame;
    static Scanner scanner = new Scanner(System.in);

    private Blackjack1.ImagePanel myImagePanel;



    
    public Blackjack(){
        initializeDeck();
        this.setOpaque(false);
        blackjack = new Blackjack1();

        myImagePanel = blackjack.imagePanel;
        
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
        while(playerPoints <=21  && !playerStays) {
            response();
            blackjack.setMessage("Player's points: " + playerPoints);
        }
        //blackjack.textArea.setText("");
        while(dealerPoints < 16){
            blackjack.setMessage("Dealer draws");
            blackjack.setMessage("Dealer's points: " + dealerPoints);
            dealerDraws();
        }
        
        //tally
        blackjack.setMessage("");
        blackjack.setMessage("Results: ");
        blackjack.setMessage("Player has " + playerPoints+ " points");
        blackjack.setMessage("Dealer has " + dealerPoints + " points");
        
        //winner: 
        results();
        playAgain();
    }

    public void initializeDeck(){
        String[] difSuits = {"Diamonds","Clubs","Hearts","Spades"};
        int imgIndex = 1;
        for(int x =1;x<=4;x++){
            for(int i = 1; i <= 13; i++){
                Card c = new Card(i, difSuits[x-1],imgIndex);
                imgIndex++;
                cards.add(c);
            }

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
    public Card drawPlayerCard(){
                int r = (int)(Math.random() * numCards);
        numCards--;
        Card c = cards.get(r);
        usedCards.add(c);
        cards.remove(r);
        try {
            Image myImage = ImageIO.read(new File("card_" + c.getImgIndex() + ".jpg"));
            myImagePanel.setSingleImage(myImage);
        } catch(IOException e) {
            System.err.println(e);
        }
        return c; 
    }
   public void playerDraws(){
        Card card = drawPlayerCard();
        int val = card.getValue();
        if(val > 10)
            val = 10;
        if (val == 1){
            if (playerPoints <22)
                playerPoints +=11;
            else {
                playerPoints++;
            }
            val=0;
        }
        playerPoints += val;
        if (blackjack != null) {
            blackjack.showCard(path + "card_" + card.getImgIndex() + ".jpg");
        }
   }

   public void dealerDraws(){
        Card dealers = drawCard();
        int val = dealers.getValue();
        if(val > 10)
            val = 10;
        if(val == 1){

            if(dealerPoints < 22)
                dealerPoints += 11;
            else {
                dealerPoints ++;
            }
            val=0;
        }
        dealerPoints += val;

   }
public void playAgain(){
    if (askPlayer("Play Again?").equals("Yes"))
        new Blackjack();
}
   public String askPlayer(String m){

        String s = (String)JOptionPane.showInputDialog(
            new JFrame(),
            m,
            "Input Dialog",
            JOptionPane.PLAIN_MESSAGE
        );
        return s;

    
   }

//what the player wants to do..
   public void response(){
    if (askPlayer("Hit or Stay").equals("Stay"))
        playerStays = true;
    else 
        playerDraws();
   }
   
   public void results(){
        if (playerPoints > dealerPoints && playerPoints < 22){
            blackjack.setMessage("You win");
        } else if (dealerPoints > playerPoints && dealerPoints < 22){
            blackjack.setMessage("Dealer wins");
        } else if (dealerPoints < playerPoints && playerPoints> 21){
            blackjack.setMessage("You busted");
            playerBusted = true;
        } else if (playerPoints< dealerPoints && dealerPoints > 21){
            blackjack.setMessage("You win");
        } else {
            blackjack.setMessage("You tied");
            new Blackjack();
        } 
   }
}



    //Array list with all cards
    //Pull a random card and remove that card from the deck
    //Calculate winner and scores
    //J-Frame
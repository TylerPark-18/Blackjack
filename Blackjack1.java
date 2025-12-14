
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Blackjack1 extends JFrame implements ActionListener{
    Card card;
    Blackjack blackjack1;
    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    public ImagePanel imagePanel;
    public JTextPane textArea;
    private int loopslot = 0;
    private String[] files;
    private Image[] allPics;
    private ArrayList<Image> pics;
    private Timer timer;
    
    

    private static final String imageBase = "card_images/";
    
    public static void main(String args[]) {
        Blackjack blackjack = new Blackjack();   
    }
    
    public Blackjack1() {
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
        
        init();
    }
    private String base;
    public void init() {
        String curDir = System.getProperty("user.dir");
        
        base = curDir + "/" + imageBase;
        pics = new ArrayList<Image>();
        timer = new Timer(400, this);
        //timer.setInitialDelay(1000);

        getAllImages();
        
        setBackground();
        //setImage("angel");      
        //setMessage("Hello, and Welcome!");
    }
    
    public void createGUI() {
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.white);
       
        //Make 2 JLayeredPanes, not ImagePanel
        // imagePanel = new ImagePanel();
        // int width = 200;
        // int height = 200;
        // imagePanel.setPreferredSize(new Dimension(width, height));
        // imagePanel.setMinimumSize(new Dimension(width, height));
        // imagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        // GridBagConstraints c = new GridBagConstraints();
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.gridx = 1;
        // c.gridy = 1;
        // contentPane.add(imagePanel,c);
        imagePanel = new ImagePanel();
        int width = 200;
        int height = 200;
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        JScrollPane imageScroll = new JScrollPane(
        imagePanel,
        JScrollPane.VERTICAL_SCROLLBAR_NEVER,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        imageScroll.setPreferredSize(new Dimension(width, height));
        imageScroll.setMinimumSize(new Dimension(width, height));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;

        contentPane.add(imageScroll, c);
        //TextPane
        textArea = new JTextPane();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(width, height/2));
        scroll.setSize(new Dimension(width, height/2));
        textArea.setPreferredSize(new Dimension(width, height/2));
        textArea.setSize(new Dimension(width, height/2));
        Font f = new Font(Font.SANS_SERIF,Font.BOLD, 16);
        textArea.setFont(f);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 20;
        contentPane.add(scroll, c);
    
        setLocationRelativeTo(null);
        setVisible(true);
        //toFront();
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
    }
    
    private void setBackground() {
        Image backImage = createImage(base+"background.jpg", "");
        ((JComponent) getContentPane()).setBorder(new CentredBackgroundBorder(backImage));
    }
    
    protected Image createImage(String path, String description) {
           return new ImageIcon(path, description).getImage();
    }

    public void setImage(String mood) {
        timer.stop();
        pics.clear();
        getImages(card.getPath());
        timer.start();
    }

     public void actionPerformed(ActionEvent e) {
            loopslot++;

            if (loopslot >= pics.size()) {
                loopslot = 0;
            }

            imagePanel.repaint();

            if (loopslot == pics.size()) {
                timer.restart();
            }
        }

    public void getAllImages() {
        File dir = new File(base);   
        files  = dir.list();
        allPics = new Image[files.length];
        System.out.println("======");
        System.out.println(base);
        for (int i = 0; i < files.length; i++) {
            //System.err.println(files[i]);
            allPics[i]=createImage(base + files[i],"");
            //System.err.println(files[i]);
            
        }
        //System.err.println(pics.size());
    }       
    public void setImageIndex(){
        
    }     
        
    public void getImages(final String path) {
    
        for (int i = 0; i < files.length; i++) {
            if (files[i].startsWith("card_" + path)) {
                pics.add(allPics[i]);
            }
        }
        //System.err.println(pics.size());
    }
    
    public void setMessage(String message) {
        String current = textArea.getText();
        textArea.setText(current + "\n" + message);
        textArea.select(current.length(), (current.length() + message.length() + 1));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    
    public class ImagePanel extends JPanel {
        private Image singleImage;
        private Image backImage;

        private static class CardImage {
            Image image;
            boolean hidden;
            CardImage(Image image, boolean hidden){
                this.image = image;
                this.hidden = hidden;
            }
        
        }
        public ImagePanel( ) {
            super();
            try {
                backImage = ImageIO.read(new File("card_images/card_back.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayList<CardImage> dealerimages = new ArrayList<>();
        ArrayList<CardImage> playerimages = new ArrayList<>();
        public void setSingleImage(Image img) {
            this.singleImage = img;
        }
        public void addDealerCard(Image img, boolean hidden){
            dealerimages.add(new CardImage(img, hidden));
            revalidate();
            repaint();
        }
        public void addPlayerCard(Image img){
            playerimages.add(new CardImage(img, false));
            revalidate();
            repaint();

        }
        public void revealDealerCard(){
            for (CardImage c : dealerimages)
                c.hidden = false;
            repaint();
        }
        public void clearImages(){
            dealerimages.clear();
            playerimages.clear();
            repaint();
        }
        @SuppressWarnings("SizeReplaceableByIsEmpty")
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x = 10;
            int dealery = 0;
            int playery = 0;
            int cardWidth= 100;
            int cardHeight = 180;
            int spacing = 100;
            
            for (CardImage img : playerimages){
                g.drawImage(img.image, x,playery,cardWidth, cardHeight,this);
                x +=spacing;
            }
            x= 10;
            for (CardImage img : dealerimages){
                Image image = img.hidden ? backImage : img.image;
                g.drawImage(img.image, x,playery,cardWidth, cardHeight,this);
                x +=spacing;
            }
        }
        @Override
    public Dimension getPreferredSize() {
        int cardWidth = 100;
        int spacing = 100;
        int height = 230;
        int maxCards = Math.max(dealerimages.size(), playerimages.size());
        int width = Math.max(1,maxCards)* spacing+20;
        return new Dimension(width, height);
}
    }

    /**
     * Show a single card image given a full or relative path (e.g. "card_images/1.jpg").
     */
    public void showCard(String imagePath) {
        if (imagePath == null) return;
        File imgFile = new File(imagePath);
        if (!imgFile.exists()) {
            System.err.println("Image not found: " + imagePath);
            return;
        } else {
            System.out.println("Showing image: " + imagePath);
        }
        Image img = new ImageIcon(imagePath).getImage();
        if (imagePanel != null) {
            imagePanel.setSingleImage(img);
            imagePanel.repaint();
        }
    }

    public class CentredBackgroundBorder implements Border {
        private final Image image;
     
        public CentredBackgroundBorder(Image image) {
            this.image = image;
        }
     
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawImage(image, 0, 0, Blackjack1.this.getWidth(), Blackjack1.this.getHeight(),null);
        }
     
        public Insets getBorderInsets(Component c) {
            return new Insets(0,0,0,0);
        }
     
        public boolean isBorderOpaque() {
            return true;
        }
    }
}
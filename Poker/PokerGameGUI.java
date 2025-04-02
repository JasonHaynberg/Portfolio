import javax.swing.*;
import java.awt.*;
import java.util.ArrayList; 
import java.util.Collections;
import java.awt.event.*;

public class PokerGameGUI extends JFrame {
    private JPanel gamePanel;
    private JLabel player1Label, player2Label;
    private JLabel resultLabel;
    private JButton dealButton;
    private Deck deck;
    private Hand player1Hand, player2Hand;
    private ArrayList<JLabel> player1CardLabels;
    private ArrayList<JLabel> player2CardLabels;

    public PokerGameGUI() {
        // Initialize the frame
        setTitle("Poker Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        gamePanel = new JPanel();
        gamePanel.setLayout(null);  // Absolute layout for positioning cards

        player1Label = new JLabel("Player 1's Hand: ");
        player2Label = new JLabel("Player 2's Hand: ");
        resultLabel = new JLabel("Result: ");
        dealButton = new JButton("Deal Cards");

        // Add components to the panel
        player1Label.setBounds(20, 20, 250, 30);
        player2Label.setBounds(20, 185, 250, 30);
        resultLabel.setBounds(20, 375, 400, 30);
        dealButton.setBounds(450, 500, 120, 30);

        gamePanel.add(player1Label);
        gamePanel.add(player2Label);
        gamePanel.add(resultLabel);
        gamePanel.add(dealButton);

        // Add game panel to frame
        add(gamePanel);

        // Initialize game objects
        deck = new Deck();
        player1Hand = new Hand();
        player2Hand = new Hand();
        player1CardLabels = new ArrayList<>();
        player2CardLabels = new ArrayList<>();

        // Set up button action to deal cards
        dealButton.addActionListener(e -> dealCards());
    }

    // Method to deal cards and update the GUI with animations
public void dealCards() {
    // Shuffle and deal 5 cards to each player
    deck.shuffle();

    player1Hand.clear(); // Clear the previous hand before adding new cards
    player2Hand.clear(); // Clear the previous hand before adding new cards
    
    // Add 5 cards to each player
    for (int i = 0; i < 5; i++) {
        player1Hand.addCard(deck.dealCard());
        player2Hand.addCard(deck.dealCard());
    }

    // Clear existing card labels
    player1CardLabels.clear();
    player2CardLabels.clear();
    gamePanel.removeAll();
    

    // Add static components to the panel again
    gamePanel.add(player1Label);
    gamePanel.add(player2Label);
    gamePanel.add(resultLabel);
    gamePanel.add(dealButton);

    // Create card labels for each player's hand and start animation
    animateDealCards();

    gamePanel.revalidate();
    gamePanel.repaint();
}


  
// Animate the cards being dealt to each player
private void animateDealCards() {
    int player1StartX = 20;
    int player2StartX = 20;
    int yPosition = 60;

    // Create player 1 cards and animate
    for (int i = 0; i < player1Hand.getHand().size(); i++) {
        String cardImagePath = getCardImagePath(player1Hand.getHand().get(i));
        JLabel cardLabel = createCardLabel(cardImagePath);
        cardLabel.setBounds(player1StartX, yPosition, 80, 120);
        gamePanel.add(cardLabel);
        player1CardLabels.add(cardLabel);
        animateCardMovement(cardLabel, player1StartX + 100 * i, yPosition, 1000);
    }

    // Create player 2 cards and animate
    for (int i = 0; i < player2Hand.getHand().size(); i++) {
        String cardImagePath = getCardImagePath(player2Hand.getHand().get(i));
        JLabel cardLabel = createCardLabel(cardImagePath);
        cardLabel.setBounds(player2StartX, yPosition, 80, 120);
        gamePanel.add(cardLabel);
        player2CardLabels.add(cardLabel);
        animateCardMovement(cardLabel, player2StartX + 100 * i, yPosition + 175, 1000);
    }

    // Revalidate and repaint the panel to show the changes
    gamePanel.revalidate();
    gamePanel.repaint();

    // Update the result after the cards are dealt
    String player1HandType = player1Hand.evaluateHand();
    String player2HandType = player2Hand.evaluateHand();
    resultLabel.setText("Result: Player 1 - " + player1HandType + " | Player 2 - " + player2HandType);

    // Determine the winner
    int comparison = player1Hand.compareHands(player2Hand);
    if (comparison > 0) {
        resultLabel.setText(resultLabel.getText() + " | Player 1 Wins!");
    } else if (comparison < 0) {
        resultLabel.setText(resultLabel.getText() + " | Player 2 Wins!");
    } else {
        resultLabel.setText(resultLabel.getText() + " | It's a tie!");
    }
}


    // // Helper method to animate the card movement
    private void animateCardMovement(JLabel cardLabel, int targetX, int targetY, int duration) {
        Timer timer = new Timer(10, new ActionListener() {
            private long startTime = System.currentTimeMillis();
            private int startX = cardLabel.getX();
            private int startY = cardLabel.getY();

            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                double progress = Math.min(1.0, elapsedTime / (double) duration);

                int newX = (int) (startX + (targetX - startX) * progress);
                int newY = (int) (startY + (targetY - startY) * progress);

                cardLabel.setBounds(newX, newY, cardLabel.getWidth(), cardLabel.getHeight());

                if (progress == 1.0) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    // Helper method to get the image path for a card based on rank and suit
    private String getCardImagePath(Card card) {
        return "images/" + card.getRank() + "_of_" + card.getSuit() + ".png";
    }

    // Helper method to create a JLabel with a card image
    private JLabel createCardLabel(String imagePath) {
    // Load the image
    ImageIcon originalIcon = new ImageIcon(imagePath);

    // Resize the image to fit the desired width and height
    Image image = originalIcon.getImage();
    Image scaledImage = image.getScaledInstance(80, 120, Image.SCALE_SMOOTH);  // Resize to 80x120

    // Create a new ImageIcon with the resized image
    ImageIcon scaledIcon = new ImageIcon(scaledImage);

    // Return a JLabel with the resized image
    return new JLabel(scaledIcon);
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PokerGameGUI gui = new PokerGameGUI();
            gui.setVisible(true);
        });
    }
}

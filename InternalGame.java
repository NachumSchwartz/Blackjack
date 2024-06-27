import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// internal game logic
public class InternalGame {
    private int wins; //number of wins
    private int losses; //number of losses
    private int ties; //number of ties
    private Deck deck; //deck of cards
    private Hand playerHand; //player's hand
    private Hand dealerHand; //dealer's hand
    private String gameState;

    private GamePanel gui; //game panel
    private boolean test;

    private enum Outcomes {
        WIN,
        LOSS,
        TIE
    }

    public InternalGame(GamePanel gui, boolean test) {//constructor - test is true if game is in test mode
        this.wins = 0; //initialize wins
        this.losses = 0; //initialize losses
        this.ties = 0; //initialize ties
        this.gui = gui; //initialize gui
        this.test = test; //initialize test
    }

    //setters and getters
    void setNewGameStats() {
        wins = 0; //set wins to 0
        losses = 0; //set losses to 0
        ties = 0; //set ties to 0
    }

    void setDeck() {
        if (test) {//if test is true, test deck is instantiated
            deck = new Deck(test);
        } else {
            deck = new Deck(); //create a new deck
        }
    }

    int getWins() {
        return wins; //return wins
    }

    int getLosses() {
        return losses; //return losses
    }

    int getTies() {
        return ties; //return ties
    }

    Deck getDeck() {
        return deck; //return deck
    }

    Hand getPlayerHand(){
        return playerHand; //return player's hand
    }
    Hand getDealerHand(){
        return dealerHand; //return dealer's hand
    }

    int getPlayerScore() {
        return playerHand.calculateHand();
    }

    int getDealerScore() {
        return dealerHand.calculateHand();
    }

    //game methods
    void deal() {
        //if current deck is too small, create a new deck
        if (deck.getDeckSize() < 20) {
            setDeck(); //set new deck
        }

        playerHand = new Hand(test); //create a new player's hand
        dealerHand = new Hand(test); //create a new dealer's hand

        playerHand.receiveCard(deck.drawCard()); //player receives a card
        playerHand.receiveCard(deck.drawCard()); //player receives a card
        dealerHand.receiveCard(deck.drawCard()); //dealer receives a card
        dealerHand.receiveCard(deck.drawCard()); //dealer receives a card

        gui.showGameButtons(); //show game buttons
        gui.getDealButton().setEnabled(false);//disable the Deal button after clicked
        gui.updatePanelsDeal(); //update cards panels on GUI

        //check for blackjack after first deal
        if (playerHand.calculateHand() == 21) {
            if (dealerHand.calculateHand() == 21) { // if dealer also == 21 --> TIE, else --> player WIN
                concludeRound(Outcomes.TIE);
            } else {
                concludeRound(Outcomes.WIN);
            }
        }
    }

    void hit() { //method to hit
        playerHand.receiveCard(deck.drawCard()); //player receives a card
        gui.updatePlayerPanelHit(); //update card panel

        if (playerHand.calculateHand() > 21) { //if player's hand > 21 --> player busts --> LOSS
            concludeRound(Outcomes.LOSS);
        } else if (playerHand.calculateHand() == 21) { //if player's hand == 21 --> check if dealer == 21
            if (dealerHand.calculateHand() == 21) { // if dealer also == 21 --> TIE, else --> player WIN
                concludeRound(Outcomes.TIE);
            } else {
                concludeRound(Outcomes.WIN);
            }
        }
    }

    void stand() { //method to stand
        // Dealer's turn
        while (dealerHand.calculateHand() < 17) { //while dealer's hand is less than 17
            dealerHand.receiveCard(deck.drawCard()); //dealer receives a card
        }

        if (dealerHand.calculateHand() > 21 ||
                playerHand.calculateHand() > dealerHand.calculateHand()) {
            concludeRound(Outcomes.WIN);
        } else if (playerHand.calculateHand() < dealerHand.calculateHand()) {
            concludeRound(Outcomes.LOSS);
        } else if (playerHand.calculateHand() == dealerHand.calculateHand()) {
            concludeRound(Outcomes.TIE);
        }
    }

    void hint() { //method to give hint
        // Simple hint logic: recommend hitting if the player's hand is 16 or less
        if (playerHand.calculateHand() <= 16) { //if player's hand is less than or equal to 16
            JOptionPane.showMessageDialog(gui, "Hint: You should hit!");
        } else {
            JOptionPane.showMessageDialog(gui, "Hint: You should stand!");
        }
    }

    private void concludeRound(Outcomes outcome) { //method to update stats, display message and reset GUI
        gui.updateDealersCardPanel(); //update dealer's card panel

        switch (outcome) {
            case WIN:
                wins++; // increment instance variable "wins"
                JOptionPane.showMessageDialog(gui, "You win!");
                break;
            case LOSS:
                losses++; // increment instance variable "losses"
                JOptionPane.showMessageDialog(gui, "Dealer wins!");
                break;
            case TIE:
                ties++; // increment instance variable "ties"
                JOptionPane.showMessageDialog(gui, "It's a tie!");
                break;
        }

        gui.resetGame(); //reset GUI
    }

    //database methods
    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public void updateGameStateWithTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy: HH:mm:ss'Z'", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.gameState = dateFormat.format(new Date());
    }
    
}

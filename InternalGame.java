import javax.swing.*;
import java.util.Map;

// internal game logic
public class InternalGame {
    private final int SMALLEST_GOOD_DECK = 20;
    private final int BLACKJACK = 21;
    private final int DEALER_STAND = 17;

    static enum AppStates{
        BRAND_NEW_GAME,
        MID_ROUND,
        AFTER_ROUND
    }

    private GamePanel gui; 
    private boolean test;

    private int wins; 
    private int losses; 
    private int ties; 
    private Deck deck; 
    private Hand playerHand;
    private Hand dealerHand;
    private AppStates appState;

    private enum Outcomes {
        WIN,
        LOSS,
        TIE
    }

    public InternalGame(GamePanel gui, boolean test) {//constructor - test is true if game is in test mode
        this.gui = gui; 
        this.test = test;
    }

    //setters and getters
    void setNewGame() {
        wins = 0; 
        losses = 0; 
        ties = 0; 

        playerHand = new Hand(test); 
        dealerHand = new Hand(test); 

        setDeck();

        appState = AppStates.BRAND_NEW_GAME;
    }

    void setDeck() {
        if (test) {//if test is true, test deck is instantiated
            deck = new Deck(test);
        } else {
            deck = new Deck(); 
        }
    }

    void setAppState(AppStates appState){
        this.appState = appState;
    }

    int getWins() {
        return wins;
    }

    int getLosses() {
        return losses; 
    }

    int getTies() {
        return ties; 
    }

    Deck getDeck() {
        return deck; 
    }

    Hand getPlayerHand(){
        return playerHand; 
    }
    Hand getDealerHand(){
        return dealerHand; 
    }

    int getPlayerScore() {
        return playerHand.calculateHand();
    }

    int getDealerScore() {
        return dealerHand.calculateHand();
    }

    AppStates getAppState(){
        return appState;
    }

    //game methods
    void deal() {
        appState = AppStates.MID_ROUND;

        //if current deck is too small, create a new deck
        if (deck.getDeckSize() < SMALLEST_GOOD_DECK) {
            setDeck(); 
        }

        playerHand.receiveCard(deck.drawCard()); 
        playerHand.receiveCard(deck.drawCard()); 
        dealerHand.receiveCard(deck.drawCard()); 
        dealerHand.receiveCard(deck.drawCard()); 

        gui.updateGUIafterDeal(); //update GUI

        //check for blackjack after first deal
        if (playerHand.calculateHand() == BLACKJACK) {
            if (dealerHand.calculateHand() == BLACKJACK) { // if dealer also == 21 --> TIE, else --> player WIN
                concludeRound(Outcomes.TIE);
            } else {
                concludeRound(Outcomes.WIN);
            }
        }
    }

    void hit() { //method to hit
        playerHand.receiveCard(deck.drawCard()); //player receives a card
        gui.updatePlayersCardPanel(); //update card panel on GUI

        if(test){
            System.out.println(playerHand);
            System.out.println(playerHand.calculateHand());
        }

        if (playerHand.calculateHand() > BLACKJACK) { //if player's hand > 21 --> player busts --> LOSS
            concludeRound(Outcomes.LOSS);
        } else if (playerHand.calculateHand() == BLACKJACK) { //if player's hand == 21 --> check if dealer == 21
            if (dealerHand.calculateHand() == BLACKJACK) { // if dealer also == 21 --> TIE, else --> player WIN
                concludeRound(Outcomes.TIE);
            } else {
                concludeRound(Outcomes.WIN);
            }
        }
    }

    void stand() { //method to stand
        // Dealer's turn
        hitForDealer(); //hit for dealer until 17

        if (dealerHand.calculateHand() > BLACKJACK ||
                playerHand.calculateHand() > dealerHand.calculateHand()) {
            concludeRound(Outcomes.WIN);
        } else if (playerHand.calculateHand() < dealerHand.calculateHand()) {
            concludeRound(Outcomes.LOSS);
        } else if (playerHand.calculateHand() == dealerHand.calculateHand()) {
            concludeRound(Outcomes.TIE);
        }
    }
    private void hitForDealer(){ //for dealer's turn to hit
        while (dealerHand.calculateHand() < DEALER_STAND) { //while dealer's hand is less than 17
            dealerHand.receiveCard(deck.drawCard()); //dealer receives a card
        }
    }

    void provideHint() { //method to give hint
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

        clearHands();
        appState = AppStates.AFTER_ROUND;
        gui.resetGui(); //reset GUI
    }

    private void clearHands(){
        playerHand.clearCards();
        dealerHand.clearCards();
    } 

    //database method
    void loadGameState(Map<String, Object> gameState) {
        setDeck();

        this.wins = (int) gameState.get("wins");
        this.losses = (int) gameState.get("losses");
        this.ties = (int) gameState.get("ties");

        String playerHandString = (String) gameState.get("playerHand");
        String dealerHandString = (String) gameState.get("dealerHand");

        if(!playerHandString.equals("empty")){
            this.playerHand = Hand.fromString(playerHandString, test);
            this.dealerHand = Hand.fromString(dealerHandString, test);
        }
    }
}

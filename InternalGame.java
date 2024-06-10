import javax.swing.JOptionPane;

public class InternalGame{
    private int wins;
    private int losses;
    private int ties;
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private GamePanel gui;

    public InternalGame(GamePanel gui){
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.gui = gui;
    }
    
    //setters and getters
    void setNewGameStats(){
        wins = 0;
        losses = 0;
        ties = 0;
    }
    void setDeck(){
        deck = new Deck();
    }
    int getWins(){
        return wins;
    }
    int getLosses(){
        return losses;
    }
    int getTies(){
        return ties;
    }
    Deck getDeck(){
        return deck;
    }
    Hand getPlayerHand(){
        return playerHand;
    }
    Hand getDealerHand(){
        return dealerHand;
    }

    //game methods
    void deal(){
        //if current deck is too small, create a new deck
        if(deck.getDeckSize() < 20){
            setDeck();
        }

        playerHand = new Hand();
        dealerHand = new Hand();
        
        playerHand.receiveCard(deck.drawCard());
        playerHand.receiveCard(deck.drawCard());
        dealerHand.receiveCard(deck.drawCard());
        dealerHand.receiveCard(deck.drawCard());

        gui.showGameButtons();
        gui.getDealButton().setEnabled(false);//disable the Deal button after clicked
        gui.updateCardPanels();
    }

    void hit() {
		playerHand.receiveCard(deck.drawCard());
		gui.updateCardPanels();
		// Check for player's bust
		if (playerHand.calculateHand() > 21) {
			JOptionPane.showMessageDialog(gui, "You busted!");
			gui.resetGame();
		}
	}

    void stand() {
		// Dealer's turn
		while (dealerHand.calculateHand() < 17) {
			dealerHand.receiveCard(deck.drawCard());
		}
		gui.updateCardPanels();
		determineWinner();
	}

    void hint() {
		// Simple hint logic: recommend hitting if the player's hand is 16 or less
		if (playerHand.calculateHand() <= 16) {
			JOptionPane.showMessageDialog(gui, "Hint: You should hit!");
		} else {
			JOptionPane.showMessageDialog(gui, "Hint: You should stand!");
		}
	}

    private void determineWinner() {
		int playerTotal = playerHand.calculateHand();
		int dealerTotal = dealerHand.calculateHand();
		if (dealerTotal > 21 || playerTotal > dealerTotal) {
			JOptionPane.showMessageDialog(gui, "You win!");
		} else if (playerTotal < dealerTotal) {
			JOptionPane.showMessageDialog(gui, "Dealer wins!");
		} else {
			JOptionPane.showMessageDialog(gui, "It's a tie!");
		}
		gui.resetGame();
	}
}

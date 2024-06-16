import javax.swing.JOptionPane;

// internal game logic
public class InternalGame{
    private int wins; //number of wins
    private int losses; //number of losses
    private int ties; //number of ties
    private Deck deck; //deck of cards
    private Hand playerHand; //player's hand
    private Hand dealerHand; //dealer's hand
    private GamePanel gui; //game panel
    boolean test;

    public InternalGame(GamePanel gui, boolean test){//test is true if game is in test mode //constructor
        this.wins = 0; //initialize wins
        this.losses = 0; //initialize losses
        this.ties = 0; //initialize ties
        this.gui = gui; //initialize gui
        this.test = test;
    }
    
    //setters and getters
    void setNewGameStats(){
        wins = 0; //set wins to 0
        losses = 0; //set losses to 0
        ties = 0; //set ties to 0
    }
    void setDeck(){
        if(test){//if test is true, test deck is instantiated
            deck = new Deck(test);
        }else{
            deck = new Deck(); //create a new deck
        }
    }
    int getWins(){
        return wins; //return wins
    }
    int getLosses(){
        return losses; //return losses
    }
    int getTies(){
        return ties; //return ties
    }
    Deck getDeck(){
        return deck; //return deck
    }
    Hand getPlayerHand(){
        return playerHand; //return player's hand
    }
    Hand getDealerHand(){
        return dealerHand; //return dealer's hand
    }

    //game methods
    void deal(){
        //if current deck is too small, create a new deck
        if(deck.getDeckSize() < 20){
            setDeck(); //set new deck
        }

        playerHand = new Hand(); //create a new player's hand
        dealerHand = new Hand(); //create a new dealer's hand
        
        playerHand.receiveCard(deck.drawCard()); //player receives a card
        playerHand.receiveCard(deck.drawCard()); //player receives a card
        dealerHand.receiveCard(deck.drawCard()); //dealer receives a card
        dealerHand.receiveCard(deck.drawCard()); //dealer receives a card

        gui.showGameButtons(); //show game buttons
        gui.getDealButton().setEnabled(false);//disable the Deal button after clicked
        gui.updateCardPanels(); //update card panels
    }

    void hit() { //method to hit
		playerHand.receiveCard(deck.drawCard()); //player receives a card
		gui.updateCardPanels(); //update card panels
		// Check for player's bust after hitting
		if (playerHand.calculateHand() > 21) { //if player's hand is greater than 21
			JOptionPane.showMessageDialog(gui, "You busted!"); //show message
			gui.resetGame(); //reset game
		}
	}

    void stand() { //method to stand
		// Dealer's turn
		while (dealerHand.calculateHand() < 17) { //while dealer's hand is less than 17
			dealerHand.receiveCard(deck.drawCard()); //dealer receives a card
		}
		gui.updateCardPanels(); //update card panels
		determineWinner(); //determine the winner
	}

    void hint() { //method to give hint
		// Simple hint logic: recommend hitting if the player's hand is 16 or less
		if (playerHand.calculateHand() <= 16) { //if player's hand is less than or equal to 16
			JOptionPane.showMessageDialog(gui, "Hint: You should hit!");
		} else {
			JOptionPane.showMessageDialog(gui, "Hint: You should stand!");
		}
	}

    private void determineWinner() { //method to determine the winner
		int playerTotal = playerHand.calculateHand(); //player's total
		int dealerTotal = dealerHand.calculateHand(); //dealer's total
		if (dealerTotal > 21 || playerTotal > dealerTotal) { //if dealer's total is greater than 21 or player's total is greater than dealer's total
			JOptionPane.showMessageDialog(gui, "You win!");
		} else if (playerTotal < dealerTotal) { //if player's total is less than dealer's total
			JOptionPane.showMessageDialog(gui, "Dealer wins!");
		} else { //if it's a tie
			JOptionPane.showMessageDialog(gui, "It's a tie!");
		}
		gui.resetGame(); //reset game
	}
}

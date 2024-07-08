import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

// GamePanel class
public class GamePanel extends JPanel implements ActionListener{
	private InternalGame internal; // Internal game logic
	private GameStart startGame; // Start game logic

	private JPanel playerPanel, dealerPanel, leaderBoard;
	private JButton dealButton, hitButton, standButton, hintButton; // Game buttons
	private JLabel winsLabel, variableWinsLabel, lossesLabel, variableLossesLabel, tiesLabel, variableTiesLabel,
	playerScoreLabel, dealerScoreLabel;
	private Menu menu; // Menu object

	public GamePanel(boolean test){//test is true if game is in test mode
		internal = new InternalGame(this, test);
		startGame = new GameStart(internal, this);

		setLayout(null); // Disable layout manager for absolute positioning

		menu = new Menu();
		menu.setBounds(10, 10, 200, 30);
		menu.addActionListener(this);
		add(menu);
		
		dealButton = new JButton("Deal"); 
		dealButton.setBounds(10, 50, 100, 30);
		dealButton.addActionListener(this); 
		dealButton.setVisible(false); //hide button - it will only appear for new game
		add(dealButton); 

		//game play buttons
		hitButton = new JButton("Hit");
		standButton = new JButton("Stand"); 
		hintButton = new JButton(resizeHintIcon(new ImageIcon("images/CardHint_Img.png")));

		hitButton.setBounds(300, 500, 80, 60); 
		standButton.setBounds(400, 500, 80, 60); 
		hintButton.setBounds(710, 450, 70, 110); 

		//hide buttons until after dealing cards
		hitButton.setVisible(false); 
		standButton.setVisible(false); 
		hintButton.setVisible(false); 

		hitButton.addActionListener(this);
		standButton.addActionListener(this);
		hintButton.addActionListener(this); 

		add(hitButton); 
		add(standButton); 
		add(hintButton); 

		//labels for dealer and player's scores
		playerScoreLabel = new JLabel("Your Score: 0");
		playerScoreLabel.setBounds(340, 445, 120, 40);
		playerScoreLabel.setOpaque(true);

		dealerScoreLabel = new JLabel("Dealer's Score: ???");
		dealerScoreLabel.setBounds(340, 85, 120, 40);
		dealerScoreLabel.setOpaque(true);

		add(playerScoreLabel);
		add(dealerScoreLabel);

		playerScoreLabel.setVisible(false);
		dealerScoreLabel.setVisible(false);

		//panels to display cards
		playerPanel = new JPanel();
		dealerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(1, 11)); 
		dealerPanel.setLayout(new GridLayout(1, 11));

		playerPanel.setOpaque(false); 
		dealerPanel.setOpaque(false); 

		// Set bounds for player and dealer panels to make them flush with each other
		playerPanel.setBounds(10, 310, 760, 135); 
		dealerPanel.setBounds(10, 130, 760, 135); 

		add(playerPanel); 
		add(dealerPanel); 

		//Leaderboard Panel
		leaderBoard = new JPanel();
		leaderBoard.setLayout(new GridLayout(3, 2));
		leaderBoard.setBounds(670, 10, 100, 100);
		leaderBoard.setVisible(false);

		winsLabel = new JLabel("Wins: ");
		variableWinsLabel = new JLabel(Integer.toString(internal.getWins()));
		lossesLabel = new JLabel("Losses: ");
		variableLossesLabel = new JLabel(Integer.toString(internal.getLosses()));
		tiesLabel = new JLabel("Ties: ");
		variableTiesLabel = new JLabel(Integer.toString(internal.getTies()));

		leaderBoard.add(winsLabel);
		leaderBoard.add(variableWinsLabel);
		leaderBoard.add(lossesLabel);
		leaderBoard.add(variableLossesLabel);
		leaderBoard.add(tiesLabel);
		leaderBoard.add(variableTiesLabel);

		add(leaderBoard); // Add leaderboard to main panel
	}

	//methods to update gui

	//resets gui for new game
	void setNewGame(InternalGame.AppStates appState){//argument contains state of old game
		if(appState == null){//if no old game at all - make components visible
			leaderBoard.setVisible(true);
			dealButton.setVisible(true);
		}else if(appState == InternalGame.AppStates.BRAND_NEW_GAME){//if old game is brand new
			return; //no change necessary to gui
		}else{//if old game was in progress
			resetGui();
		}
	}

	//method to update gui after dealing cards
	void updateGUIafterDeal() {
		dealButton.setEnabled(false);

		hitButton.setVisible(true); 
		standButton.setVisible(true); 
		hintButton.setVisible(true); 

		//Display scores
		playerScoreLabel.setVisible(true);
		dealerScoreLabel.setVisible(true);
		
		updateCardPanelAfterDeal();
	}
	private void updateCardPanelAfterDeal() { //private method for cards panels
		//gui reflects list of Card in InternalGame instance
		List<Card> dealerCards = internal.getDealerHand().getCards();

		updatePlayersCardPanel();

		dealerPanel.add(new CardFace(dealerCards.get(0).getValue(), dealerCards.get(0).getSuit()));
		dealerPanel.add(new CardFace(dealerCards.get(1).getValue(), "Hidden"));
		
		playerScoreLabel.setText("Your Score: " + internal.getPlayerScore());

		revalidate();
		repaint();
	}

	void updateDealersCardPanel(){
		updateCardPanel(dealerPanel, internal.getDealerHand(), dealerScoreLabel);
	}
	void updatePlayersCardPanel(){
		updateCardPanel(playerPanel, internal.getPlayerHand(), playerScoreLabel);
	}
	private void updateCardPanel(JPanel cardPanel, Hand hand, JLabel scoreLabel) { // Update gui after stand
		cardPanel.removeAll(); //remove cards from panel before repopulating

		List<Card> cards = hand.getCards();
		for (int i = 0; i < cards.size(); i++) { // Loop through cards and create new card faces
			cardPanel.add(new CardFace(cards.get(i).getValue(), cards.get(i).getSuit()));
		}

		//update score labels
		if(scoreLabel == playerScoreLabel){
			scoreLabel.setText("Player Score: " + internal.getPlayerScore());
		}else if(scoreLabel == dealerScoreLabel){
			scoreLabel.setText("Dealer Score: " + internal.getDealerScore());
		}
			
		revalidate();
		repaint();
	}

	void resetGui() { // Reset gui to prepare for new round. Update leaderboard.

		//make game play button invisible
		hitButton.setVisible(false); 
		standButton.setVisible(false); 
		hintButton.setVisible(false); 

		dealButton.setEnabled(true); // Re-enable the Deal button for new round
		
		clearCardPanels(); // Clear card panels

		//Reset Game score labels
		playerScoreLabel.setText("Your Score: 0");
		playerScoreLabel.setVisible(false);

		dealerScoreLabel.setText("Dealer's Score: ???");
		dealerScoreLabel.setVisible(false);

		// update leaderboard
		variableWinsLabel.setText(Integer.toString(internal.getWins()));
		variableLossesLabel.setText(Integer.toString(internal.getLosses()));
		variableTiesLabel.setText(Integer.toString(internal.getTies()));
	}

	private void clearCardPanels() { // Clear card panels
		playerPanel.removeAll();
		dealerPanel.removeAll();
	}

	public void actionPerformed(ActionEvent e){  // Action listener for all GUI components
         if(e.getSource() == menu){ 
			String choice = (String) menu.getSelectedItem();
			startGame.choiceSwitch(choice); 
		 }else if(e.getSource() == dealButton){ 
			internal.deal(); 
		 }else if(e.getSource() == hitButton){ 
			internal.hit(); 
		 }else if(e.getSource() == standButton){ 
			internal.stand(); 
		 }else if(e.getSource() == hintButton){ 
			internal.provideHint(); 
		 }
    }

	//Function to resize Hint image to fit into components
	private ImageIcon resizeHintIcon(ImageIcon icon) {
		Image original = icon.getImage();
		Image resized = original.getScaledInstance(70, 110, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resized);
	}

	public void updateFromLoadedGame(InternalGame internal) {
		// Clear current panels
		clearCardPanels();

		// Update panels with loaded cards
		List<Card> playerCards = internal.getPlayerHand().getCards();
		List<Card> dealerCards = internal.getDealerHand().getCards();

		for (Card card : playerCards) {
			playerPanel.add(new CardFace(card.getValue(), card.getSuit()));
		}

		for (Card card : dealerCards) {
			dealerPanel.add(new CardFace(card.getValue(), card.getSuit()));
		}

		// Update scores
		playerScoreLabel.setText("Your Score: " + internal.getPlayerScore());
		dealerScoreLabel.setText("Dealer's Score: " + internal.getDealerScore());

		// Show scores and buttons
		playerScoreLabel.setVisible(true);
		dealerScoreLabel.setVisible(true);
		hitButton.setVisible(true);
		standButton.setVisible(true);
		hintButton.setVisible(true);

		revalidate();
		repaint();
	}
}

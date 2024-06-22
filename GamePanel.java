import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

// GamePanel class
public class GamePanel extends JPanel implements ActionListener{
	private InternalGame internal; // Internal game logic
	private GameStart startGame; // Start game logic

	private JPanel playerPanel, dealerPanel, leaderBoard;
	private JButton dealButton, hitButton, standButton, hintButton; // Game buttons
	private JLabel winsLabel, variableWinsLabel, lossesLabel, variableLossesLabel, tiesLabel, variableTiesLabel;
	private Menu menu; // Menu object

	public GamePanel(boolean test){//test is true if game is in test mode
		internal = new InternalGame(this, test);
		startGame = new GameStart(internal, this);

		setLayout(null); // Disable layout manager for absolute positioning

		menu = new Menu(); // Initialize menu
		menu.setBounds(10, 10, 200, 30); // Example position
		menu.addActionListener(this); // Add action listener
		add(menu); // Add menu to panel
		
		dealButton = new JButton("Deal"); // Initialize deal button
		dealButton.setBounds(10, 50, 100, 30); // Example position
		dealButton.addActionListener(this); // Add action listener
		dealButton.setVisible(false); // Hide button
		add(dealButton); // Add button to panel

		// Initialize game buttons
		hitButton = new JButton("Hit"); // Initialize hit button
		standButton = new JButton("Stand");  // Initialize stand button
		hintButton = new JButton(resizeHintIcon(new ImageIcon("images/CardHint_Img.png"))); // Initialize hint button

		hitButton.setBounds(300, 500, 80, 30); // Example position
		standButton.setBounds(400, 500, 80, 30); // Example position
		hintButton.setBounds(710, 450, 70, 110); // Example position

		hitButton.setVisible(false); // Hide button
		standButton.setVisible(false); // Hide button
		hintButton.setVisible(false); // Hide button

		hitButton.addActionListener(this); // Add action listener
		standButton.addActionListener(this); // Add action listener
		hintButton.addActionListener(this); // Add action listener

		add(hitButton); // Add button to panel
		add(standButton); // Add button to panel
		add(hintButton); // Add button to panel

		// Initialize card panels
		playerPanel = new JPanel(); // Initialize player panel
		dealerPanel = new JPanel(); // Initialize dealer panel
		playerPanel.setLayout(new GridLayout(1, 11)); // Set layout for player panel
		dealerPanel.setLayout(new GridLayout(1, 11)); // Set layout for dealer panel

		playerPanel.setOpaque(false); // Make the panel transparent
		dealerPanel.setOpaque(false); // Make the panel transparent

		// Set bounds for player and dealer panels to make them flush with each other
		playerPanel.setBounds(10, 310, 760, 135); // Example position
		dealerPanel.setBounds(10, 130, 760, 135); // Example position

		add(playerPanel); // Add player panel to main panel
		add(dealerPanel); // Add dealer panel to main panel

		//Leaderboard Panel
		leaderBoard = new JPanel();
		leaderBoard.setLayout(new GridLayout(3, 2));
		//leaderBoard.setOpaque(false);
		leaderBoard.setBounds(700, 10, 100, 100);
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

	void setNewGame(){
		leaderBoard.setVisible(true);
		dealButton.setVisible(true);
	}

	void showGameButtons() { // Show game buttons
		hitButton.setVisible(true); // Show button
		standButton.setVisible(true); // Show button
		hintButton.setVisible(true); // Show button
	}

	private void clearCardPanels() { // Clear card panels
		playerPanel.removeAll();
		dealerPanel.removeAll();
	}

	void updatePlayerPanel() { // Update player card panel
		playerPanel.removeAll(); //remove cards from panel before repopulating
		List<Card> playerCards = internal.getPlayerHand().getCards(); // Get player cards
		for (int i = 0; i < playerCards.size(); i++) { // Loop through player cards and create new card faces
			playerPanel.add(new CardFace(playerCards.get(i).getValue(), playerCards.get(i).getSuit()));
		}

		revalidate();
		repaint();
	}
	void updateDealerPanel() { // Update dealer card panel
		dealerPanel.removeAll(); //remove cards from panel before repopulating

		List<Card> dealerCards = internal.getDealerHand().getCards(); // Get player cards
		for (int i = 0; i < dealerCards.size(); i++) { // Loop through player cards and create new card faces
			dealerPanel.add(new CardFace(dealerCards.get(i).getValue(), dealerCards.get(i).getSuit()));
		}

		revalidate();
		repaint();
	}

	void resetGame() { // Reset game
		hitButton.setVisible(false); // Hide game buttons
		standButton.setVisible(false); // Hide game buttons
		hintButton.setVisible(false); // Hide game buttons
		dealButton.setEnabled(true); // Re-enable the Deal button for new round
		
		clearCardPanels(); // Clear card panels

		// update leaderboard
		variableWinsLabel.setText(Integer.toString(internal.getWins()));
		variableLossesLabel.setText(Integer.toString(internal.getLosses()));
		variableTiesLabel.setText(Integer.toString(internal.getTies()));
	}

	JButton getDealButton(){ // Get deal button
		return dealButton; // Return deal button
	}

	public void actionPerformed(ActionEvent e){  // Action listener
         if(e.getSource() == menu){ // If menu is selected
			String choice = (String) menu.getSelectedItem(); //	Get selected item
			startGame.choiceSwitch(choice); // Switch to selected item
		 }else if(e.getSource() == dealButton){ // If deal button is selected
			internal.deal(); // Deal
		 }else if(e.getSource() == hitButton){ // If hit button is selected
			internal.hit(); // Hit
		 }else if(e.getSource() == standButton){ //	If stand button is selected
			internal.stand(); // Stand
		 }else if(e.getSource() == hintButton){ // If hint button is selected
			internal.hint(); // Hint
		 }
    }

	//Function to resize Hint image to fit into components
	private ImageIcon resizeHintIcon(ImageIcon icon) {
		Image original = icon.getImage();
		Image resized = original.getScaledInstance(70, 110, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resized);
	}
}

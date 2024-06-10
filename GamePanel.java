import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel implements ActionListener{
	private InternalGame internal = new InternalGame(this);
	private GameStart startGame = new GameStart(internal);

	private List<JLabel> playerCardPanels;
	private List<JLabel> dealerCardPanels;
	private JButton dealButton, hitButton, standButton, hintButton;
	private Menu menu; 

	public GamePanel(){
		setLayout(null); // Disable layout manager for absolute positioning

		menu = new Menu();
		menu.setBounds(10, 10, 200, 30); // Example position
		menu.addActionListener(this);
		add(menu);
		
		dealButton = new JButton("Deal");
		dealButton.setBounds(10, 50, 100, 30); // Example position
		dealButton.addActionListener(this);
		add(dealButton);

		// Initialize game buttons
		hitButton = new JButton("Hit");
		standButton = new JButton("Stand");
		hintButton = new JButton("Hint");

		hitButton.setBounds(300, 500, 80, 30);
		standButton.setBounds(400, 500, 80, 30);
		hintButton.setBounds(500, 500, 80, 30);

		hitButton.setVisible(false);
		standButton.setVisible(false);
		hintButton.setVisible(false);

		hitButton.addActionListener(this);
		standButton.addActionListener(this);
		hintButton.addActionListener(this);

		add(hitButton);
		add(standButton);
		add(hintButton);

		// Initialize card panels
		playerCardPanels = new ArrayList<>();
		dealerCardPanels = new ArrayList<>();
		JPanel playerPanel = new JPanel();
		JPanel dealerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(1, 11));
		dealerPanel.setLayout(new GridLayout(1, 11));

		playerPanel.setOpaque(false); // Make the panel transparent
		dealerPanel.setOpaque(false); // Make the panel transparent

		for (int i = 0; i < 11; i++) {
			JLabel playerCardLabel = new JLabel("", SwingConstants.CENTER);
			playerCardPanels.add(playerCardLabel);
			playerPanel.add(playerCardLabel);

			JLabel dealerCardLabel = new JLabel("", SwingConstants.CENTER);
			dealerCardPanels.add(dealerCardLabel);
			dealerPanel.add(dealerCardLabel);
		}

		// Set bounds for player and dealer panels to make them flush with each other
		playerPanel.setBounds(10, 350, 760, 100); // Example position
		dealerPanel.setBounds(10, 200, 760, 100); // Example position

		add(playerPanel);
		add(dealerPanel);
	}

	void showGameButtons() {
		hitButton.setVisible(true);
		standButton.setVisible(true);
		hintButton.setVisible(true);
	}

	private void clearCardPanels() {
		// Clear player card panels
		for (JLabel label : playerCardPanels) {
			label.setText("");
		}

		// Clear dealer card panels
		for (JLabel label : dealerCardPanels) {
			label.setText("");
		}
	}

	void updateCardPanels() {
		List<Card> playerCards = internal.getPlayerHand().getCards();
		for (int i = 0; i < playerCardPanels.size(); i++) {
			if (i < playerCards.size()) {
				playerCardPanels.get(i).setText(playerCards.get(i).toString());
			} else {
				playerCardPanels.get(i).setText("");
			}
		}

		List<Card> dealerCards = internal.getDealerHand().getCards();
		for (int i = 0; i < dealerCardPanels.size(); i++) {
			if (i < dealerCards.size()) {
				if (i == 0) {
					dealerCardPanels.get(i).setText(dealerCards.get(i).toString());
				} else {
					dealerCardPanels.get(i).setText("Hidden");
				}
			} else {
				dealerCardPanels.get(i).setText("");
			}
		}
	}

	void resetGame() {
		hitButton.setVisible(false);
		standButton.setVisible(false);
		hintButton.setVisible(false);
		dealButton.setEnabled(true); // Re-enable the Deal button for new game
		clearCardPanels();
	}

	JButton getDealButton(){
		return dealButton;
	}

	public void actionPerformed(ActionEvent e){  
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
			internal.hint();
		 }
    }
}

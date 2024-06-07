/**This is the main class that structures the main
 * Blackjack GUI, which houses the main menu functions,
 * start a new game or continue an old one, and check leaderboard.
 */

/**Jake Shoemake
 * Eric Gittens
 * Nachum Schwartz
 * Paul Owens
 * 
 * 6/4/2024
 * CMSC 495
 * Blackjack Project
 */

import javax.swing.*;

public class Main extends JFrame {
	//Icons
	private static ImageIcon gameIcon, bgIcon;
	private static JLabel background;
	private static GamePanel gamePanel;

	public Main() {		
		//Image icons
		gameIcon = new ImageIcon("images/blackjack_icon.png");
		this.setIconImage(gameIcon.getImage());
		
		bgIcon = new ImageIcon("images/background.png");
		background = new JLabel(bgIcon);
		background.setBounds(0, 0, 1220, 780);
		add(background);

		//Game Panel
		gamePanel = new GamePanel();
		add(gamePanel);
		
		//Frame properties
		this.setTitle("Blackjack");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(1235, 815);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Main mainGame = new Main();
	}
}

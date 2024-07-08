import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

// Create a class that extends JFrame
public class GameFrame extends JFrame{
	private JLayeredPane layeredPane;
	private JLabel backgroundLabel;
	private GamePanel gamePanel;

	public GameFrame(boolean test){//test is true if game is in test mode
		setTitle("Blackjack"); // title of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("images/blackjack_icon.png").getImage()); //icon of the frame
		setSize(800, 600);
		setLocationRelativeTo(null); //center frame in middle of screen
		setBackground(new java.awt.Color(38, 95, 35, 255));

		// Create a layered pane
		layeredPane = new JLayeredPane();
		setContentPane(layeredPane);

		// Add background label to the layered pane
		backgroundLabel = new JLabel(new ImageIcon("images/background.png")); //create background image
		backgroundLabel.setBounds(0, 0,getWidth(), getHeight()); 
		layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

		// Create and add the game panel
		gamePanel = new GamePanel(test);
		gamePanel.setOpaque(false); // Set the panel to be transparent
		gamePanel.setBounds(0, 0, getWidth(), getHeight());
		layeredPane.add(gamePanel, JLayeredPane.PALETTE_LAYER);
		
		setVisible(true);
	}
}

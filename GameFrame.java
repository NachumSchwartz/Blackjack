import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

// Create a class that extends JFrame
public class GameFrame extends JFrame{
        public GameFrame(boolean test){//test is true if game is in test mode
            setTitle("Blackjack"); // Set the title of the frame
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
            setIconImage(new ImageIcon("images/blackjack_icon.png").getImage()); // Set the icon of the frame
            setSize(800, 600); // Set the size of the frame
			setLocationRelativeTo(null);
			setBackground(new java.awt.Color(38, 95, 35, 255));

            // Create a layered pane
		    JLayeredPane layeredPane = new JLayeredPane(); // Create a layered pane
		    setContentPane(layeredPane); // Set the content pane of the frame to the layered pane

            // Add background label to the layered pane
		    JLabel backgroundLabel = new JLabel(new ImageIcon("images/background.png")); // Create a label with a background image
		    backgroundLabel.setBounds(0, 0,getWidth(), getHeight()); // Set the bounds of the label
		    layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER); // Add the label to the layered pane

            // Create and add the game panel
		    GamePanel gamePanel = new GamePanel(test); //Create a new game panel
		    gamePanel.setOpaque(false); // Set the panel to be transparent
		    gamePanel.setBounds(0, 0, getWidth(), getHeight()); // Set the bounds of the panel
		    layeredPane.add(gamePanel, JLayeredPane.PALETTE_LAYER); // Add the panel to the layered pane
            
		    setVisible(true);// Make the frame visible
        }
}

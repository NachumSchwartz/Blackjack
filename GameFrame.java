import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class GameFrame extends JFrame{
        public GameFrame(){
            setTitle("Blackjack");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setIconImage(new ImageIcon("images/blackjack_icon.png").getImage());
            setSize(800, 600);

            // Create a layered pane
		    JLayeredPane layeredPane = new JLayeredPane();
		    setContentPane(layeredPane);

            // Add background label to the layered pane
		    JLabel backgroundLabel = new JLabel(new ImageIcon("images/background.png"));
		    backgroundLabel.setBounds(0, 0,getWidth(), getHeight());
		    layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

            // Create and add the game panel
		    GamePanel gamePanel = new GamePanel();
		    gamePanel.setOpaque(false);
		    gamePanel.setBounds(0, 0, getWidth(), getHeight());
		    layeredPane.add(gamePanel, JLayeredPane.PALETTE_LAYER);
            
            // Make the frame visible
		    setVisible(true);
        }
}

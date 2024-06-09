import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
	private static CardFace[] playerCards, dealerCards;
	private static CardBack deckImage;
	private static Deck deck;
	private static JButton dealButton, hitButton, standButton, hintButton;
	private static JLabel winsLabel, lossesLabel, tiesLabel, winsVariableLabel, lossesVariableLabel, tiesVariableLabel;
	private static Menu menu; 
	private static GameMenuAL gmal;
	private static DealButtonAL dealbal;
	private static HitButtonAL hitbal;
	private static StandButtonAL standbal;
	private static HintButtonAL hintbal;

	public GamePanel(){
		menu = new Menu();
		gmal = new GameMenuAL(menu);
		menu.addActionListener(gmal);
		this.add(menu);
	}
}

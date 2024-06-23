import javax.swing.*;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

// CardFace class
public class CardFace extends JPanel{
    private JLabel valueText, cardLabel;

    // CardFace constructor
    public CardFace(String value, String suit){
        setLayout(null);
        setOpaque(false);
        
        //set image for suit
        String imagePath = "";

        switch(suit){
            case "Hearts":
                imagePath = "images/CardHeart_Img.png";
                break;
            case "Diamonds":
                imagePath = "images/CardDiamond_Img.png";
                break;
            case "Spades":
                imagePath = "images/CardSpade_Img.png";
                break;
            case "Clubs":
                imagePath = "images/CardClub_Img.png";
                break;
            case "Hidden":
                imagePath = "images/CardBack_Img.png";
                break;
        }

        //Customizes Card value text to be displayed (if cardface is not hidden)
        if(!suit.equals("Hidden")) {
            valueText = new JLabel(value);

            //If the suit is colored red, change color from default of black to red
            if(suit.equals("Hearts") || suit.equals("Diamonds")) {
                valueText.setForeground(new Color(255, 0, 0));
            }

            valueText.setFont(new Font("Serif", Font.BOLD, 30));

            add(valueText);
            valueText.setBounds(6, 1, 50, 35);
        }

        //Loads custom card image
        cardLabel = new JLabel(resizeSuitIcon(new ImageIcon(imagePath)));
        cardLabel.setBounds(0, 0, 84, 128);
        add(cardLabel);
    }

    //Function to resize Suit image
	private ImageIcon resizeSuitIcon(ImageIcon icon) {
		Image original = icon.getImage();
		Image resized = original.getScaledInstance(84, 128, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resized);
	}
}
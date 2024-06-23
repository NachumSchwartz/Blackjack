import javax.swing.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;

import java.awt.Color;

// CardFace class
public class CardFace extends JPanel{
    private String value; // value of the card
    private String suit; // suit of the card
    private Color color; // color of the card

    private JLabel valueText, cardLabel;

    // CardFace constructor
    public CardFace(String value, String suit){
        this.value = value; // set value
        this.suit = suit; // set suit

        //set color for border
        if(suit.equals("Hearts")||suit.equals("Diamonds")){ // if suit is Hearts or Diamonds
            this.color = Color.red; // set color to red
        }else if(suit.equals("Spades")||suit.equals("Clubs")){ // if suit is Spades or Clubs
            this.color = Color.black; // set color to black
        }

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

        setLayout(null);
        //setPreferredSize(new Dimension(50, 70)); // set preferred size
        setOpaque(false);
        
        //Customizes Card value text to be displayed
        if(!suit.equalsIgnoreCase("Hidden")) {
            valueText = new JLabel(value);

            //If the suit is colored red
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
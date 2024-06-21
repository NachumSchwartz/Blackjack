import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Dimension;

import java.awt.Color;

// CardFace class
public class CardFace extends JPanel{
    private String value; // value of the card
    private String suit; // suit of the card
    private Color color; // color of the card

    // CardFace constructor
    public CardFace(String value, String suit){
        this.value = value; // set value
        this.suit = suit; // set suit
        if(suit.equals("Hearts")||suit.equals("Diamonds")){ // if suit is Hearts or Diamonds
            this.color = Color.red; // set color to red
        }else if(suit.equals("Spades")||suit.equals("Clubs")){ // if suit is Spades or Clubs
            this.color = Color.black; // set color to black
        }

        // create borders
        Border colorBorder = BorderFactory.createMatteBorder(10, 10, 10, 10, this.color); // create matte border
        Border emptyBorder = BorderFactory.createEmptyBorder(2,2,2,2); // create empty border
        Border compoundOutside = BorderFactory.createCompoundBorder(colorBorder, emptyBorder); // create compound border
        Border wholeBorder = BorderFactory.createCompoundBorder(compoundOutside, colorBorder); // create compound border

        setBorder(wholeBorder); // set border
        setPreferredSize(new Dimension(50, 70)); // set preferred size
        setBackground(Color.white); // set background color
        
        add(new JLabel(value));
    }
}
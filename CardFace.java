import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import java.awt.Color;

public class CardFace extends JPanel{
    private String value;
    private String suit;
    private Color color;

    public CardFace(String value, String suit, Color color){
        this.value = value;
        this.suit = suit;
        this.color = color;

        Border colorBorder = BorderFactory.createMatteBorder(10, 10, 10, 10, color);
        Border emptyBorder = BorderFactory.createEmptyBorder(2,2,2,2);
        Border compoundOutside = BorderFactory.createCompoundBorder(colorBorder, emptyBorder);
        Border wholeBorder = BorderFactory.createCompoundBorder(compoundOutside, colorBorder);

        setBorder(wholeBorder);
        setPreferredSize(new Dimension(200, 300));
        setBackground(Color.white);
        setLayout(new GridBagLayout());
        
        Emblem emblem = new Emblem(value, suit, color); 
    
        add(emblem); 
    }
}
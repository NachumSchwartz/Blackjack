// class to represent a card in a deck of cards
public class Card {
    private String value; // the value of the card
    private String suit; // the suit of the card

    public Card(String value, String suit) { // constructor
        this.value = value;
        this.suit = suit; 
    }

    String getValue() { 
        return value;
    }

    String getSuit() {
        return suit; 
    }

    @Override
    public String toString() { 
        return value;
    }
}

// class to represent a card in a deck of cards
public class Card {
    private String value; // the value of the card
    private String suit; // the suit of the card

    public Card(String value, String suit) { // constructor
        this.value = value; // set the value of the card
        this.suit = suit; // set the suit of the card
    }

    public String getValue() { // get the value of the card
        return value; // return the value of the card
    }

    public String getSuit() { // get the suit of the card
        return suit; // return the suit of the card
    }

    @Override
    public String toString() { // override the toString method
        return value; // return the value of the card
    }
}

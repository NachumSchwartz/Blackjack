import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a deck of cards
public class Deck {
    private List<Card> deck; // the deck of cards
    private final int NUM_OF_DECKS = 2; // the number of decks

    public Deck() {// constructor
        deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"}; 
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; 

        //create large deck with multiple sets of suits 
        for(int i = 1; i <= NUM_OF_DECKS; i++){ // for each deck
            for (String suit : suits) { // for each suit
                for (String value : values) { // for each value
                    deck.add(new Card(value, suit)); // add a new card to the deck
                }
            }
        }
        shuffleDeck(); // shuffle the deck
    }

    //for test mode change values to test values
    //this deck is not shuffled
    public Deck(boolean test){ //constructor with boolean parameter
        deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"A","A","A","A","A","A","A","A","A","A"};

        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(value, suit));
            }
        }
    }
    
    private void shuffleDeck() {// shuffle the deck
        Collections.shuffle(deck);
    }

    Card drawCard() { // draw a card from the deck
        return deck.remove(deck.size() - 1); // remove and return the last card in the deck
    }

    int getDeckSize() { // get the size of the deck
        return deck.size(); // return the size of the deck
    }
}

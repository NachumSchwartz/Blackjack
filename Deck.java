import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String value : values) {
                Deck.add(new Card(value, suit));
            }
        }
        shuffleDeck();
    }
    
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        return deck.remove(deck.size() - 1);
    }

    public int getDeckSize() {
        return deck.size();
    }
}

import java.util.ArrayList;
import java.util.List;

// Represents a hand of cards
public class Hand {
    private List<Card> cards;  // the cards in the hand

    public Hand(){// constructor
        cards = new ArrayList<Card>(); // create a new ArrayList
    }

    void receiveCard(Card card){ // receive a card
        cards.add(card); // add the card to the hand
    }

    int calculateHand() { // calculate the value of the hand
        int total = 0; // the total value of the hand
        int aceCount = 0; // the number of aces in the hand
        for (Card card : cards) { // for each card in the hand
            int cardValue; // the value of the card
            switch (card.getValue()) { // switch on the value of the card
                case "J": // if the card is a Jack
                case "Q": // if the card is a Queen
                case "K": // if the card is a King
                    cardValue = 10; // set the value of the card to 10
                    break;
                case "A": // if the card is an Ace
                    cardValue = 11; // set the value of the card to 11
                    aceCount++; // increment the number of aces
                    break;
                default:
                    cardValue = Integer.parseInt(card.getValue()); // set the value of the card to the integer value of the card
                    break;
            }
            total += cardValue; // add the value of the card to the total
        }

        // Handle Aces dynamically
        while (total > 21 && aceCount > 0) { // while the total is greater than 21 and there are aces in the hand
            total -= 10; // subtract 10 from the total
            aceCount--; // decrement the number of aces
        }

        return total; // return the total value of the hand
    }

    List<Card> getCards() { // get the cards in the hand
        return cards; // return the cards in the hand
    }
    
    @Override
    public String toString(){
        String handValues = ""; // the values of the cards in the hand
        for(Card card : cards){ // for each card in the hand
            handValues += card.getValue() + ","; // add the value of the card to the handValues string
        }
        return handValues; // return the values of the cards in the hand
    }
}

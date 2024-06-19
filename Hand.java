import java.util.ArrayList;
import java.util.List;

// Represents a hand of cards
public class Hand {
    private List<Card> cards;  // the cards in the hand
    private int aceCount = 0; // the number of aces in the hand
    private boolean test; // "test" is true if game is in test mode

    public Hand(boolean test){// constructor
        cards = new ArrayList<Card>(); // create a new ArrayList
        this.test = test;
    }

    void receiveCard(Card card){ // receive a card
        cards.add(card); // add the card to the hand

        //if an Ace is received, increment Hand's aceCount
        if(card.getValue().equals("A")){
            aceCount++;
        }
    }

    int calculateHand() { // calculate the value of the hand
        int total = 0; // the total value of the hand
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
                    break;
                default:
                    cardValue = Integer.parseInt(card.getValue()); // set the value of the card to the integer value of the card
                    break;
            }
            total += cardValue; // add the value of the card to the total
        }
    
        if(test){ // test mode print out
            System.out.println("Total before Aces handling: " + total);
        }
        
        
        // Handle Aces dynamically
        for(int i = 1; total > 21 && i <= aceCount; i++){// while the total is greater than 21 and there are aces in the hand
            total -= 10;
        }
                     
        if(test){ // test mode print out
            System.out.println("Total after Aces handling: " + total);
        }

        return total; // return the total value of the hand
    }

    List<Card> getCards() { // get the cards in the hand
        return cards; // return the cards in the hand
    }

    int getAceCount(){ // get the number of aces in the hand
        return aceCount; // return the number of aces in the hand
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

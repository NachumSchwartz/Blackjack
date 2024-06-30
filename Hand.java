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
        for(int i = 1; total > 21 && i <= aceCount; i++){// if the total is > 21, subtract 10 per Ace until total is <= 21
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

    Card getLastCard(){
        return cards.get(cards.size() - 1);
    }

    int getAceCount(){ // get the number of aces in the hand
        return aceCount; // return the number of aces in the hand
    }
    
/*    @Override
    public String toString(){
        String handValues = ""; // the values of the cards in the hand
        for(Card card : cards){ // for each card in the hand
            handValues += card.toString() + ","; // add the value of the card to the handValues string
        }
        return handValues; // return the values of the cards in the hand
    }*/

    @Override
    public String toString() { // convert hand to string
        StringBuilder handValues = new StringBuilder(); // the values of the cards in the hand
        for (Card card : cards) { // for each card in the hand
            handValues.append(card.getValue()).append("-").append(card.getSuit()).append(","); // add the value and suit of the card to the handValues string
        }
        if (handValues.length() > 0) {
            handValues.setLength(handValues.length() - 1); // remove the last comma
        }
        return handValues.toString(); // return the values of the cards in the hand
    }

    public static Hand fromString(String handString, boolean test) { // create hand from string
        Hand hand = new Hand(test);
        if (handString != null && !handString.isEmpty()) {
            String[] cardStrings = handString.split(",");
            for (String cardString : cardStrings) {
                String[] parts = cardString.split("-");
                if (parts.length == 2) {
                    hand.receiveCard(new Card(parts[0], parts[1]));
                }
            }
        }
        return hand;
    }
}

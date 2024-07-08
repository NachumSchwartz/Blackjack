import java.util.ArrayList;
import java.util.List;

// Represents a hand of cards
public class Hand {
    private List<Card> cards;  // the cards in the hand
    private int aceCount = 0; // the number of aces in the hand
    private boolean test; // "test" is true if game is in test mode

    public Hand(boolean test){// constructor
        cards = new ArrayList<Card>();
        this.test = test;
    }

    void receiveCard(Card card){ 
        cards.add(card);

        //if an Ace is received, increment Hand's aceCount
        if(card.getValue().equals("A")){
            aceCount++;
        }
    }

    int calculateHand() { // calculate the value of the hand
        int total = 0; 
        for (Card card : cards) { 
            int cardValue; 
            switch (card.getValue()) {
                case "J": 
                case "Q": 
                case "K":
                    cardValue = 10; 
                    break;
                case "A": 
                    cardValue = 11; 
                    break;
                default: //for non-face cards
                    // set the value of the card to the integer value of the card
                    cardValue = Integer.parseInt(card.getValue()); 
                    break;
            }
            total += cardValue; // add the value of the card to the total
        }
    
        if(test){ // test mode print out
            System.out.println("Total before Aces handling: " + total);
        }
        
        
        // Handle Aces dynamically
        // if the total is > 21, subtract 10 per Ace until total is <= 21
        for(int i = 1; total > 21 && i <= aceCount; i++){
            total -= 10;
        }
                     
        if(test){ // test mode print out
            System.out.println("Total after Aces handling: " + total);
        }

        return total; // return the total value of the hand
    }

    void clearCards(){ //remove all cards from hand
        cards.clear();
        aceCount = 0;
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
    
    @Override
    public String toString() { // convert hand to string
        StringBuilder handValues = new StringBuilder(); 
        for (Card card : cards) { 
            handValues.append(card.getValue()).append("-").append(card.getSuit()).append(","); 
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

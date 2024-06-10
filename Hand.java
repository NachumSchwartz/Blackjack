import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand(){
        cards = new ArrayList<Card>();
    }

    void receiveCard(Card card){
        cards.add(card);
    }

    int calculateHand() {
        int total = 0;
        int aceCount = 0;
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
                    aceCount++;
                    break;
                default:
                    cardValue = Integer.parseInt(card.getValue());
                    break;
            }
            total += cardValue;
        }

        // Handle Aces dynamically
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    List<Card> getCards() {
        return cards;
    }
    
    @Override
    public String toString(){
        String handValues = "";
        for(Card card : cards){
            handValues += card.getValue() + ",";
        }
        return handValues;
    }
}

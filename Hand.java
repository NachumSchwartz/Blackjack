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

    int calculateHand(){
        int total = 0;
        for(Card card : cards){
            total += convertCardValue(card.getValue());
        }

        return total;
    }

    private int convertCardValue(String value){
        int numValue = 0;
        switch(value){
            case "J","Q","K","A":
                numValue = 10;
                break;
            default:
                numValue = Integer.parseInt(value);
        }
        return numValue;
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

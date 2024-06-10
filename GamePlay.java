public class GamePlay {
    static void deal(){
        System.out.println("In deal");
        //deal button only works if round is not in play
        if(!InternalGameObjects.getRoundInPlay()){
            InternalGameObjects.setRoundInPlay();
            Deck deck = InternalGameObjects.getDeck();

            //if current deck is too small, create a new deck
            if(deck.getDeckSize() < 20){
                InternalGameObjects.setDeck();//sets IGO deck to a new deck
            }
    
            InternalGameObjects.setPlayerHand();
            InternalGameObjects.setDealerHand();
    
            InternalGameObjects.getPlayerHand().receiveCard(deck.drawCard());
            InternalGameObjects.getPlayerHand().receiveCard(deck.drawCard());
            InternalGameObjects.getDealerHand().receiveCard(deck.drawCard());
            InternalGameObjects.getDealerHand().receiveCard(deck.drawCard());

            System.out.println("In round");

            System.out.println(InternalGameObjects.getPlayerHand());
            System.out.println(InternalGameObjects.getDealerHand());
        }
    }
}

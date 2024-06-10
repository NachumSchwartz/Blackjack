public class InternalGameObjects {
    private static int wins = 0;
    private static int losses = 0;
    private static int ties = 0;
    private static Deck deck;
    private static Hand playerHand;
    private static Hand dealerHand;
    private static boolean roundInPlay = false;

    static void setNewGameStats(){
        wins = 0;
        losses = 0;
        ties = 0;
    }
    static void setDeck(){
        deck = new Deck();
    }
    static void setPlayerHand(){
        playerHand = new Hand();
    } 
    static void setDealerHand(){
        dealerHand = new Hand();
    } 
    static void setRoundInPlay(){
        roundInPlay = true;
    }
    static int getWins(){
        return wins;
    }
    static int getLosses(){
        return losses;
    }
    static int getTies(){
        return ties;
    }
    static Deck getDeck(){
        return deck;
    }
    static Hand getPlayerHand(){
        return playerHand;
    }
    static Hand getDealerHand(){
        return dealerHand;
    }
    static boolean getRoundInPlay(){
        return roundInPlay;
    }
}

public class InternalGameObjects {
    private static int wins = 0;
    private static int losses = 0;
    private static int ties = 0;
    private static Deck deck;

    static void setNewGameStats(){
        wins = 0;
        losses = 0;
        ties = 0;
    }
    static void setDeck(){
        deck = new Deck();
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
}

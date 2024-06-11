public class GameStart {
    InternalGame internalGame;
    GamePanel gui;

    public GameStart(InternalGame internalGame, GamePanel gui){
        this.internalGame = internalGame;
        this.gui = gui;
    }

    void choiceSwitch(String choice){
        switch (choice) {
            case "New Game":
                newGame();
                break;
            case "Load Game":
                loadGame();
                break;
            case "Save Game":
                saveGame();
                break;
            case "Tutorial":
                tutorial();
                break;
        }
    }
    private void newGame(){
        internalGame.setNewGameStats();
        internalGame.setDeck();
        gui.getDealButton().setVisible(true);
    }

    private void loadGame(){
        System.out.println("Load Game");
    }
    private void saveGame(){
        System.out.println("Save Game");
    }
    private void tutorial(){
        System.out.println("Tutorial");
    }

}
 
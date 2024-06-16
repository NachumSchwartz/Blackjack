// Purpose: This class is used to start the game and switch between the different options in the menu.
public class GameStart {
    InternalGame internalGame; //InternalGame object
    GamePanel gui; //GamePanel object

    public GameStart(InternalGame internalGame, GamePanel gui){ //constructor
        this.internalGame = internalGame; //initialize internalGame
        this.gui = gui; //initialize gui
    }

    void choiceSwitch(String choice){ //method to switch between the different options in the menu
        switch (choice) { //switch statement
            case "New Game": //case for New Game
                newGame(); //call newGame method
                break;
            case "Load Game": //case for Load Game
                loadGame(); //call loadGame method
                break;
            case "Save Game": //case for Save Game
                saveGame(); //call saveGame method
                break;
            case "Tutorial": //case for Tutorial
                tutorial(); //call tutorial method
                break;
        }
    }
    private void newGame(){ //method to start a new game
        internalGame.setNewGameStats(); //set new game stats
        internalGame.setDeck(); //set new deck
        gui.getDealButton().setVisible(true); //set deal button to visible
    }

    private void loadGame(){ //method to load a game
        System.out.println("Load Game");
    }
    private void saveGame(){ //method to save a game
        System.out.println("Save Game");
    }
    private void tutorial(){ //method to display the tutorial
        System.out.println("Tutorial");
    }

}
 
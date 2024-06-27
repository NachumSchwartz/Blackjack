import javax.swing.*;

// Purpose: This class is used to start the game and switch between the different options in the menu.
public class GameStart {
    private InternalGame internalGame; //InternalGame object
    private GamePanel gui; //GamePanel object

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
        gui.setNewGame(); // set up GUI with new game
    }

    private void loadGame(){ //method to load a game
        System.out.println("Load Game");
    }
    private void saveGame(){ //method to save a game
        System.out.println("Save Game");
        DatabaseManager.initializeDatabase();
        // Set the current game state with timestamp
        internalGame.updateGameStateWithTimestamp();

        // Get the current game state from internalGame
        int playerScore = internalGame.getPlayerScore();
        int dealerScore = internalGame.getDealerScore();
        String playerHand = String.valueOf(internalGame.getPlayerHand());
        String dealerHand = String.valueOf(internalGame.getDealerHand());
        String gameState = internalGame.getGameState();
        int wins = internalGame.getWins();
        int losses = internalGame.getLosses();
        int ties = internalGame.getTies();

        // Save the game state using DatabaseManager
        //DatabaseManager.initializeDatabase();
        //DatabaseManager.saveGameState(playerScore, dealerScore, playerHand, dealerHand, gameState);
        DatabaseManager.saveGameState(playerScore, dealerScore, playerHand, dealerHand, gameState, wins, losses, ties);

        JOptionPane.showMessageDialog(null, "Game state saved successfully!");
        //DatabaseManager.connect();
        //DatabaseManager.createTable();
        //DatabaseManager.saveGameState(internalGame.getDeck().toString(), internalGame.getWins(), internalGame.getLosses(), internalGame.getTies());

    }
    private void tutorial(){ //method to display the tutorial
        System.out.println("Tutorial");
    }
}
 
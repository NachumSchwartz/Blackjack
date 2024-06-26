import javax.swing.*;
import java.util.Map;

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

/*    private void loadGame(){ //method to load a game
        SaveLoadPanel inputPanel = new SaveLoadPanel("load");
        JOptionPane.showMessageDialog(gui,inputPanel,"",JOptionPane.PLAIN_MESSAGE);
        String gameName = inputPanel.getGameName();
        if(gameName != null){
            System.out.println(gameName);



        } 
    }*/

    private void loadGame() { // Method to load a game
        SaveLoadPanel inputPanel = new SaveLoadPanel("load");
        JOptionPane.showMessageDialog(gui, inputPanel, "", JOptionPane.PLAIN_MESSAGE);
        String gameName = inputPanel.getGameName();
        if (gameName != null) {
            Map<String, Object> gameState = DatabaseManager.getGameStateByGameName(gameName);
            if (!gameState.isEmpty()) {
                internalGame.loadGameState(gameState);
                gui.updateFromLoadedGame(internalGame);
                JOptionPane.showMessageDialog(gui, "Game loaded successfully!");
            } else {
                JOptionPane.showMessageDialog(gui, "No saved game found with the name: " + gameName);
            }
        }
    }


    private void saveGame(){ //method to save a game
        SaveLoadPanel inputPanel = new SaveLoadPanel("save");
        JOptionPane.showMessageDialog(gui,inputPanel,"",JOptionPane.PLAIN_MESSAGE);
        String gameName = inputPanel.getGameName();
        
        if(gameName != null && gameName.length() > 0){
            System.out.println(gameName);

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

            DatabaseManager.saveGameState(gameName, playerScore, dealerScore, playerHand, dealerHand, gameState, wins, losses, ties);

            JOptionPane.showMessageDialog(null, "Game state saved successfully!");
        }
    }
    private void tutorial(){ //method to display the tutorial
        System.out.println("Tutorial");
    }
}
 
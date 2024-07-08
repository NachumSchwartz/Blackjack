import javax.swing.*;
import java.util.Map;

//Purpose: This class is used to start the game and switch between the different options in the menu.
public class GameStart {
    private InternalGame internalGame; 
    private GamePanel gui; 

    public GameStart(InternalGame internalGame, GamePanel gui){ //constructor
        this.internalGame = internalGame; 
        this.gui = gui; 
    }

    void choiceSwitch(String choice){ //method to switch between the different options in the menu
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
    private void newGame(){ //method to start a new game

        //if there is already a game in play get confirmation for a new game
        if(internalGame.getAppState() == InternalGame.AppStates.MID_ROUND || 
        internalGame.getAppState() == InternalGame.AppStates.AFTER_ROUND){
            int response = JOptionPane.showConfirmDialog(gui,
                                                    "Do you want to begin a new game?",
                                                    "New Game?",
                                                    JOptionPane.YES_NO_OPTION);

            //if response is not positive - exit method - don't start new game                                          
            if(response != JOptionPane.YES_OPTION){
                return;
            }
        }
 
        //set a new game for internal engine and gui
        InternalGame.AppStates oldAppState = internalGame.getAppState(); //record the old state before resetting
        internalGame.setNewGame(); //resets game stats
        gui.setNewGame(oldAppState); //full reset if game was in progress - if not then elements made visible
    }

    private void loadGame() { // Method to load a game
        SaveLoadPanel inputPanel = new SaveLoadPanel("load");
        JOptionPane.showMessageDialog(gui, inputPanel, "", JOptionPane.PLAIN_MESSAGE);
        String gameName = inputPanel.getGameName();
        if (gameName != null) {
            Map<String, Object> gameState = DatabaseManager.getGameStateByGameName(gameName);
            if (!gameState.isEmpty()) {//check that there is a game with such a name
                internalGame.loadGameState(gameState);
                JOptionPane.showMessageDialog(gui, "Game loaded successfully!");
            } else {
                JOptionPane.showMessageDialog(gui, "No saved game found with the name: " + gameName);
            }
        }
    }
    
    private void saveGame(){ //method to save a game
        //only save if game has begun
        if(internalGame.getAppState() == InternalGame.AppStates.BRAND_NEW_GAME ||
            internalGame.getAppState() == null){
            return;
        }

        SaveLoadPanel inputPanel = new SaveLoadPanel("save");
        JOptionPane.showMessageDialog(gui,inputPanel,"",JOptionPane.PLAIN_MESSAGE);
        String gameName = inputPanel.getGameName();
        
        if(gameName != null && gameName.length() > 0){
            DatabaseManager.initializeDatabase();

            // Get the current game state from internalGame
            int playerScore = internalGame.getPlayerScore();
            int dealerScore = internalGame.getDealerScore();
            String playerHand = String.valueOf(internalGame.getPlayerHand());
            String dealerHand = String.valueOf(internalGame.getDealerHand());

            //if there are no cards in hands, set String as "empty"
            if(playerHand.equals("")){
                playerHand = "empty";
                dealerHand = "empty";
            }

            int wins = internalGame.getWins();
            int losses = internalGame.getLosses();
            int ties = internalGame.getTies();

            DatabaseManager.saveGameState(gameName, playerScore, dealerScore, playerHand, dealerHand, wins, losses, ties);

            JOptionPane.showMessageDialog(null, "Game state saved successfully!");
        }
    }
    private void tutorial(){ //method to display the tutorial
        System.out.println("Tutorial");
    }
}
 
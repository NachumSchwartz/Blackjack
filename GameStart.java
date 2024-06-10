public class GameStart {
    static void choiceSwitch(String choice){
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
    private static void newGame(){
        InternalGameObjects.setNewGameStats();
        InternalGameObjects.setDeck();
    }

    private static void loadGame(){
        System.out.println("Load Game");
    }
    private static void saveGame(){
        System.out.println("Save Game");
    }
    private static void tutorial(){
        System.out.println("Tutorial");
    }

}
 
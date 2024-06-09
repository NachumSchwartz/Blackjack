public class GameStart {
    static void choiceSwitch(String choice){
        switch (choice) {
            case "New Game":
                newGame();
                break;
            case "Continue Game":
                continueGame();
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
        System.out.println("New Game");
    }

    private static void continueGame(){
        System.out.println("Continue Game");
    }
    private static void saveGame(){
        System.out.println("Save Game");
    }
    private static void tutorial(){
        System.out.println("Tutorial");
    }

}
 
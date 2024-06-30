/**Blackjack with a GUI
 */

/**Jake Shoemake
 * Eric Gittens
 * Nachum Schwartz
 * Paul Owen
 * 
 * 6/4/2024
 * CMSC 495
 * Blackjack Project
 */

//class that runs the game

import javax.swing.SwingUtilities;

public class Main{
    public static void main(String[] args) {
        boolean temp = false;

        //if args has "test" as parameter, game is in test mode
        //if args is empty the args[0] will not be evaluated
        if(args.length != 0 && args[0].equals("test")){
            temp = true;
        }
    
        final boolean test = temp;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameFrame frame = new GameFrame(test); // create a new GameFrame
            }
        });
    }
}


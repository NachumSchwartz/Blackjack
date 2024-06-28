import javax.swing.*;
import java.awt.GridLayout;

public class SaveLoadPanel extends JPanel{
    private JLabel instructionLabel;
    private JTextField gameNameField;
    private SavedGamesMenu menu; //custom JComboBox

    private String gameName;

    public SaveLoadPanel(String action){ //String parameter sets save/load
        setBackground(new java.awt.Color(38, 95, 35, 255));
        setLayout(new GridLayout(2,1));

        //panel for save game input
        if(action.equals("save")){
            instructionLabel = new JLabel("Please enter a name for the game:");
            add(instructionLabel);

            gameNameField = new JTextField(10);
            gameNameField.addCaretListener(e -> {//listens for change in textfield
                gameName = gameNameField.getText();
            });
            add(gameNameField);

        //panel for load game input
        }else if(action.equals("load")){
            instructionLabel = new JLabel("Please select the game to load:");
            add(instructionLabel);

            menu = new SavedGamesMenu();
            menu.addActionListener(e -> {// listens for selection in menu
                gameName = (String) menu.getSelectedItem();
            });
            add(menu);
        }
    }

    String getGameName(){
        return gameName;
    }
}

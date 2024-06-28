import javax.swing.JComboBox;

public class SavedGamesMenu extends JComboBox<String>{
    public SavedGamesMenu(){ // constructor
        super(DatabaseManager.getGameNames());//String[] of database names
        setSelectedIndex(0); // set selected index
    } 
}
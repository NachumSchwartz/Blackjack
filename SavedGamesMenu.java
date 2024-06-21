import javax.swing.JComboBox;

public class SavedGamesMenu extends JComboBox<String>{
	private static String menuOptions[]; 

    public SavedGamesMenu(){ // constructor
        super(menuOptions); // call super constructor
        setSelectedIndex(0); // set selected index
    } 
}

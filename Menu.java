import javax.swing.JComboBox;

// class that creates the menu
public class Menu extends JComboBox<String>{
	private static String menuOptions[] = {"New Game", "Load Game", "Save Game", "Tutorial"}; // menu options

    public Menu(){ // constructor
        super(menuOptions); // call super constructor
        setBounds(5, 5, 150, 40); // set bounds
        setSelectedIndex(0); // set selected index
    } 
}

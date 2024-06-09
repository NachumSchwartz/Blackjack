import javax.swing.JComboBox;

public class Menu extends JComboBox<String>{
	private static String menuOptions[] = {"New Game", "Continue Game", "Save Game", "Tutorial"};

    public Menu(){
        super(menuOptions);
        setBounds(5, 5, 150, 40);
        setSelectedIndex(0);
    } 
}

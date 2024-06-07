import javax.swing.JComboBox;

public class Menu extends JComboBox<String>{
	static String menuOptions[] = {"New Game", "Continue Game", "Save Game"};

    public Menu(){
        super(menuOptions);
        setBounds(5, 5, 150, 40);
        setSelectedIndex(0);
    } 
}

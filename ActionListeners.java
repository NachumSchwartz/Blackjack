import java.awt.event.*;
import javax.swing.JComboBox;

class GameMenuAL implements ActionListener{
    JComboBox<String> menu;
    
    public GameMenuAL(JComboBox<String> menu){
        this.menu = menu;
    }
    public void actionPerformed(ActionEvent e){
        String choice = (String) menu.getSelectedItem();
        GameStart.choiceSwitch(choice);
    }
}

class DealButtonAL implements ActionListener{
    public void actionPerformed(ActionEvent e){  
         
    }
}

class HitButtonAL implements ActionListener{
    public void actionPerformed(ActionEvent e){  
         
    }
}

class StandButtonAL implements ActionListener{
    public void actionPerformed(ActionEvent e){  
         
    }
}

class HintButtonAL implements ActionListener{
    public void actionPerformed(ActionEvent e){  
         
    }
}

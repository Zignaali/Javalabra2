package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class menuListener implements ActionListener {
    Tetris tetris;
    
    public menuListener(Tetris tetris) {
        this.tetris = tetris;
    }
    
    public void actionPerformed(ActionEvent event) {
        String choice = ((JMenuItem)event.getSource()).getText();
        
        if (choice.equals("New Game")) {
            tetris.newGame();
        }
        if (choice.equals("Exit")) {
            tetris.exitGame();
        }       
    }   
}
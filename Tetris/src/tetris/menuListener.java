package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * MenuListener is a class that listens to the menu.
 * @author Forss
 * @version %I% %G%
 * @
 * */

public class menuListener implements ActionListener {
    Tetris tetris;
    
    /**
     * Class constructor.
     * @param tetris 
     */
    public menuListener(Tetris tetris) {
        this.tetris = tetris;
    }
    /**
     * Calls for newGame() or exitGame(), depending on which button
     * the user has interacted with.
     * @param event     the action, that the user has made
     */
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
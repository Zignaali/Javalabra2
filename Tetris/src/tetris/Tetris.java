
package tetris;

import javax.swing.*;
import java.awt.*;
/**
 * Tetris is a class that creates a window and a menu for the game.
 * @author Forss
 * @version %I% %G%
 */
public class Tetris implements Runnable {
     private JFrame window;
     
     /**
      * Creates a new window
      */
     public void run() {
        window = new JFrame("Tetris");
        window.setPreferredSize(new Dimension(200, 400));
        
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.pack();
        window.setVisible(true);
     }
     
     /**
      * Creates a menu to the window.
      * @param window   contains the window, which the menu will be added to
      */
     public void createMenu(JFrame window) {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        
        JMenuItem menuNewGame = new JMenuItem("New Game");
        menuNewGame.addActionListener(new menuListener(this));
        menu.add(menuNewGame);
  
        JMenuItem menuExitGame = new JMenuItem("Exit");
        menuExitGame.addActionListener(new menuListener(this));
        menu.add(menuExitGame);
        
        window.setJMenuBar(menuBar);
     }
     /**
      * Starts the game.
      */
     public void newGame() {
         
     }
     /**
      * Disposes the window.
      */
     public void exitGame() {
         window.dispose();
     }
}

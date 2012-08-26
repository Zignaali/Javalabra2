
package tetris;

import javax.swing.*;
import java.awt.*;

public class Tetris implements Runnable {
     private JFrame window;
     
     public void run() {
        window = new JFrame("Tetris");
        window.setPreferredSize(new Dimension(200, 400));
        
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.pack();
        window.setVisible(true);
     }
     
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
     
     public void newGame() {
         
     }
     
     public void exitGame() {
         window.dispose();
     }
}

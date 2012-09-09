package tetris;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 * Creates a game window and starts the game
 * @author Forss
 */
public class Frame extends JFrame {

    JLabel statusbar;
    
    /**
     * The class constructor.
     */
    public Frame() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        board.start();
        
        setSize(200, 400);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   /**
    * 
    * @return   returns a status bar
    */
   public JLabel getStatusBar() {
       return statusbar;
   }
   
}
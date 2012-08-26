
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
}


package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyboardListener listens to the player's actions and calls for the appropriate
 * methods when an action is made.
 * @author Forss
 */
public class KeyboardListener extends KeyAdapter {
        Board board; 
        
        /**
         * The class constructor.
         * @param board     contains the board
         */
        public KeyboardListener(Board board) {
            this.board = board;
        }
        
        /**
         * Listens to certain key presses and acts according to the key
         * that was pressed.
         * @param event     contains the key code of the key that was pressed.
         */
        @Override
        public void keyPressed(KeyEvent event) {

             if (board.isStarted = false || board.curPiece.getShape() == Shape.TetrisPiece.NoShape) {  
                 return;
             }

             int keycode = event.getKeyCode();            
             
             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 board.tryMove(board.curPiece, board.curX - 1, board.curY);
                 break;
             case KeyEvent.VK_RIGHT:
                 board.tryMove(board.curPiece, board.curX + 1, board.curY);
                 break;
             case KeyEvent.VK_DOWN:
                 board.tryMove(board.curPiece.rotatePieceRight(), board.curX, board.curY);
                 break;
             case KeyEvent.VK_UP:
                 board.tryMove(board.curPiece.rotatePieceLeft(), board.curX, board.curY);
                 break;
             case KeyEvent.VK_SPACE:
                 board.dropDown();
                 break;
             }

         }
    }



package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TimerListener listens to a timer, which in this case fires every 400ms and
 * checks if a piece has finished falling or not.
 * @author Forss
 */
public class TimerListener implements ActionListener {
    Board board;
    
    /**
     * The class constructor.
     * @param board     contains the board
     */
    public TimerListener(Board board) {
        this.board = board;
    }
    
    /**
     * Checks if the piece has finished falling, then creates a new piece or moves
     * the piece one line down if it hasn't finished falling yet.
     * @param event     
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (board.isFallingFinished) {
            board.isFallingFinished = false;
            board.newPiece();
        } else {
            board.oneLineDown();
        }
    }
}

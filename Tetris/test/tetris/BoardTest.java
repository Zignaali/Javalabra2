/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Graphics;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author Forss
 */
public class BoardTest {
    
    public BoardTest() {
        
    }

    /**
     * Test of squareWidth method, of class Board.
     */
    @Test
    public void testSquareWidth() {
        Frame frame = new Frame();
        Board instance = new Board(frame);
        int expResult = (int) instance.getSize().getWidth() / instance.BoardWidth;
        int result = instance.squareWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of squareHeight method, of class Board.
     */
    @Test
    public void testSquareHeight() {
        Frame frame = new Frame();
        Board instance = new Board(frame);
        int expResult = (int) instance.getSize().getHeight() / instance.BoardHeight;
        int result = instance.squareHeight();
        assertEquals(expResult, result);
    }
}

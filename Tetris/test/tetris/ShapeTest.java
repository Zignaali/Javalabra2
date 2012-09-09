/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tetris.Shape.TetrisPiece;

/**
 *
 * @author Forss
 */
public class ShapeTest {
    
    public ShapeTest() {
        
    }

    /**
     * Test of getShape method, of class Shape.
     */
    @Test
    public void testGetShape() {
        System.out.println("getShape");
        Shape instance = new Shape();
        TetrisPiece expResult = TetrisPiece.NoShape;
        TetrisPiece result = instance.getShape();
        assertEquals(expResult, result);
    }
}
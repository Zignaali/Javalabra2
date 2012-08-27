
package tetris;

import java.util.*;

/**
 * Shape is a class that creates the different Tetrominoes and handles
 * changing the positions of the pieces. This is made using coordinates.
 * @author Forss
 * @version %I% %G%
 */
public class Shape {
    private int[][] coords;
    private int[][][] coordsTable;
    enum TetrisPiece { NoShape, ZShape, SShape, LineShape, TShape, SquareShape,
                        LShape, MirroredLShape };
    private TetrisPiece pieceShape;
    
    /**
     * The class constructor.
     */
    public Shape() {
        coords = new int[4][2];
        setShape(TetrisPiece.NoShape);
    }
    /**
     * Sets the coordinates for a piece.
     * @param shape     contains the shape of the piece
     */
    public void setShape(TetrisPiece shape) {
        coordsTable = new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } }, // NoShape
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1} }, // SShape
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } }, // ZShape
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } }, // LineShape
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } }, // TShape
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } }, // SquareShape
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } }, // MirroredLShape
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } } }; // LShape 
        
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }
        pieceShape = shape;
    }
    
    /**
     * Randomly selects the shape, that will be created.
     */
    public void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        TetrisPiece[] values = TetrisPiece.values();
        setShape(values[x]);
    }
    public TetrisPiece getShape() {
        return pieceShape;
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    public int x(int index) { 
        return coords[index][0];
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    public int y(int index) {
        return coords[index][1];
    } 
    
    public int minX() {
      int m = coords[0][0];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][0]);
      }
      return m;
    }

    public int minY() {
      int m = coords[0][1];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][1]);
      }
      return m;
    }
    
    /**
     * Returns the Z-axis coordinates of the piece.
     * @param index
     * @param x
     * @return 
     */
    
    private int setX(int index, int x) {
        return coords[index][0];
    }
    
    /**
     * Returns the Y-axis coordinates of the piece.
     * @param index
     * @param y
     * @return 
     */  
    private int setY(int index, int y) {
        return coords[index][1];
    }
    
    /**
     * Rotates the piece counter-clockwise.
     * @return result   returns the new position of the piece
     */
    public Shape rotatePieceLeft() {
        if (pieceShape == TetrisPiece.SquareShape) {
            return this;
        }
        
        Shape result = new Shape();
        result.pieceShape = pieceShape;
        
        for (int i = 0; i < 4; i++) {
            setX(i, y(i));
            setY(i, -x(i));
        }
        return result;
    }
    
    /**
     * Rotates the piece clockwise.
     * @return result   returns the new position of the piece.
     */
    public Shape rotatePieceRight() {
        if (pieceShape == TetrisPiece.SquareShape) {
            return this;
        }
        
        Shape result = new Shape();
        result.pieceShape = pieceShape;
        
        for (int i = 0; i < 4; i++) {
            setX(i, y(i));
            setY(-i, x(i));
        }
        return result;
    }
}

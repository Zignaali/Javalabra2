
package tetris;

import java.util.Random;

/**
 * Shape is a class that creates the different Tetrominoes and handles
 * rotating the pieces.
 * @author Forss
 * @version %I% %G%
 */
public final class Shape {
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
     * Sets the coordinates for shapes.
     * @param shape     contains a shape
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
     * Randomly selects a shape.
     */
    public void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        TetrisPiece[] values = TetrisPiece.values();
        setShape(values[x]);
    }
    
    /**
     * Returns the shape of the current piece.
     * @return  returns the shape of the piece
     */
    public TetrisPiece getShape() {
        return pieceShape;
    }
    
    /**
     * Returns the x-coordinates of a piece.
     * @param   index
     * @return  returns the x-coordinates of a piece.
     */
    public int x(int index) { 
        return coords[index][0];
    }
    
    /**
     * Returns the y-coordinates of a piece.
     * @param index
     * @return  returns the y-coordinates of a piece
     */
    public int y(int index) {
        return coords[index][1];
    } 
    
    /**
     * Calculates the smallest possible x-coordinate.
     * @return returns the smallest possible x-coordinate
     */
    public int minX() {
      int m = coords[0][0];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][0]);
      }
      return m;
    }
    /**
     * Calculates the smallest possible y-coordinate.
     * @return returns the smallest possible y-coordinate 
     */
    public int minY() {
      int m = coords[0][1];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][1]);
      }
      return m;
    }
    
    /**
     * Sets the x-coordinates of a piece.
     * @param index
     * @param x
     */
    
    private void setX(int index, int x) {
        coords[index][0] = x;
    }
    
    /**
     * Sets the y-coordinates of a piece
     * @param index
     * @param y 
     */
    private void setY(int index, int y) {
        coords[index][1] = y;
    }
    
    /**
     * Rotates a piece counter-clockwise.
     * @return result   returns the new position of a piece
     */
    public Shape rotatePieceLeft() {
        if (pieceShape == TetrisPiece.SquareShape) {
            return this;
        }
        
        Shape result = new Shape();
        result.pieceShape = pieceShape;
        
        for (int i = 0; i < 4; i++) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
    
    /**
     * Rotates a piece clockwise.
     * @return result   returns the new position of a piece.
     */
    public Shape rotatePieceRight() {
        if (pieceShape == TetrisPiece.SquareShape) {
            return this;
        }
        
        Shape result = new Shape();
        result.pieceShape = pieceShape;
        
        for (int i = 0; i < 4; i++) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}

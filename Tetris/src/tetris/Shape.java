
package tetris;

import java.util.*;

public class Shape {
    private int[][] coords;
    private int[][][] coordsTable;
    enum TetrisPiece { NoShape, ZShape, SShape, LineShape, TShape, SquareShape,
                        LShape, MirroredLShape };
    private TetrisPiece pieceShape;
    
    public Shape() {
        coords = new int[4][2];
        setShape(TetrisPiece.NoShape);
    }
    
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
    
    public void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        TetrisPiece[] values = TetrisPiece.values();
        setShape(values[x]);
    }
}

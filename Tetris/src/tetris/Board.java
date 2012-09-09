package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import tetris.Shape.TetrisPiece;

/**
 * This class contains the actual game logic, also paints pieces onto the board
 * @author Forss
 */
public class Board extends JPanel {


    final int BoardWidth = 10;
    final int BoardHeight = 22;

    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    JLabel statusbar;
    Shape curPiece;
    TetrisPiece[] board;


    /**
     * The class constructor.
     * @param parent    contains a JFrame
     */
    public Board(Frame parent) {

       setFocusable(true);
       curPiece = new Shape();
       timer = new Timer(400, new TimerListener(this));
       timer.start(); 

       statusbar =  parent.getStatusBar();
       board = new TetrisPiece[BoardWidth * BoardHeight];
       addKeyListener(new KeyboardListener(this));
       clearBoard();  
    }

    /**
     * Calculates the width of a square.
     * @return      returns the width of a square
     */
    public int squareWidth() {
        return (int) getSize().getWidth() / BoardWidth; 
    }
    
    /**
     * Calculates the height of a square
     * @return      returns the height of a square
     */
    public int squareHeight() {
        return (int) getSize().getHeight() / BoardHeight; 
    }
    
    /**
     * Returns the position of a piece on the board
     * @param x     contains the x-coordinate of a piece
     * @param y     contains the y-coordinate of a piece
     * @return      returns the position of a piece on the board
     */
    TetrisPiece shapeAt(int x, int y) {
        return board[(y * BoardWidth) + x]; 
    }

    /**
     * Starts the game.
     */
    public void start() {
        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();

        newPiece();
        timer.start();
    }

    /**
     * Draws all the shapes on a board
     * @param g 
     */ 
    @Override
    public void paint(Graphics g) { 
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();


        for (int i = 0; i < BoardHeight; ++i) {
            for (int j = 0; j < BoardWidth; ++j) {
                TetrisPiece shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != TetrisPiece.NoShape)
                    drawSquare(g, 0 + j * squareWidth(),
                               boardTop + i * squareHeight(), shape);
            }
        }

        if (curPiece.getShape() != TetrisPiece.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                           boardTop + (BoardHeight - y - 1) * squareHeight(),
                           curPiece.getShape());
            }
        }
    }
    /**
     * Moves a piece down one line if possible.
     */  
    public void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }
    
    /**
     * Drops the piece down as far as possible.
     */
    public void dropDown() {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    /**
     * Fills the board with empty shapes.
     */
    private void clearBoard() {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i)
            board[i] = TetrisPiece.NoShape;
    }
    
    /**
     * Puts a falling piece into the board array, tries to remove full lines and
     * tries to create a new piece.
     */
    private void pieceDropped() {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BoardWidth) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece();
    }
    
    /**
     * Creates a new piece, ends the game if player cannot move from the initial
     * positions.
     */
    public void newPiece() {
        curPiece.setRandomShape();
        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(TetrisPiece.NoShape);
            timer.stop();
            isStarted = false;
            statusbar.setText("game over");
        }
    }
    
    /**
     * Checks if a move is valid or not, then completes the move if it is valid.
     * @param newPiece  contains the shape of a piece in a new position
     * @param newX      contains the new x-coordinate of a piece
     * @param newY      contains the new y-coordinate of a piece
     * @return          returns true if the move is valid, false if not
     */
    public boolean tryMove(Shape newPiece, int newX, int newY) {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                return false;
            if (shapeAt(x, y) != TetrisPiece.NoShape)
                return false;
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }
    
    /**
     * Removes full lines and adds a point for each removed line.
     */
    private void removeFullLines() {
        int numFullLines = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == TetrisPiece.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k) {
                    for (int j = 0; j < BoardWidth; ++j)
                         board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            if (numFullLines >= 4) {
                numLinesRemoved += (numFullLines + 5);
                statusbar.setText("Tetris!");                
            } else {
                numLinesRemoved += numFullLines;
                statusbar.setText(String.valueOf(numLinesRemoved));
                isFallingFinished = true;
                curPiece.setShape(TetrisPiece.NoShape);
                repaint();
            }
        }    
     }
    
    /**
     * Draws each of the squares in a piece
     * @param g
     * @param x         contains the x-coordinate of a square
     * @param y         contains the y-coordinate of a square
     * @param shape     contains the shape of a piece
     */
    private void drawSquare(Graphics g, int x, int y, TetrisPiece shape) {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + 1);

    }
    
}
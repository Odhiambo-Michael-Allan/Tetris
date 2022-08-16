package Core;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import Layout.TetrisBoard;

/**
 * A piece is represented internally by a 5*5 array of integers with 1 indicating
 * a filled position and 0 a blank position...
 */

public abstract class Piece {


    protected ArrayList<int[][]> possibleConfigurations;
    private int positionInConfigurationsList = 0;
    private int[][] previousConfiguration;
    protected int[][] currentConfiguration;
    protected Color pieceColor;  // Every piece has its own color...
    private int PIECE_SIZE = 10;

    private int ROW_ON_TETRIS_BOARD = 0;
    private int COLUMN_ON_TETRIS_BOARD = 9;

    protected TetrisBoard tetrisBoard;

    /**
     * Subclasses must initialize a list of possible configurations...
     */
    protected abstract void initializePossibleConfigurations();

    /**
     * Subclasses must initialize a tetris board on which they will draw themselves...
     */
    protected abstract void initializeTetrisBoard( TetrisBoard board );

    // A piece knows its current configuration...
    public int[][] getCurrentConfiguration() {
        return currentConfiguration;
    }

    // A piece knows how to draw itself...
    public void draw( GraphicsContext drawingArea ) {
        // A piece needs to only draw the filled position in its array..
        // Also note that its the current configuration of the piece that is drawn...
        setColor();
        drawingArea.setFill( pieceColor );
        drawFilledPositions( drawingArea );
    }

    private void drawFilledPositions( GraphicsContext drawingArea ) {
        for ( int row = 0; row < getCurrentConfiguration().length; row++ )
            for ( int col = 0; col < getCurrentConfiguration().length; col++ )
                if ( getCurrentConfiguration()[row][col] == 1 )
                    drawingArea.fillRect( (col+COLUMN_ON_TETRIS_BOARD)*10, (row+ROW_ON_TETRIS_BOARD)*10, PIECE_SIZE, PIECE_SIZE );
    }

    /**
     * Subclasses will override this method to specify a different color...
     */
    protected void setColor() {
        pieceColor = Color.YELLOW;  // The default color is yellow...
    }

    public void proceedToTheNextConfiguration() {
        previousConfiguration = possibleConfigurations.get( positionInConfigurationsList );
        positionInConfigurationsList++;
        positionInConfigurationsList = positionInConfigurationsList % possibleConfigurations.size();
        currentConfiguration = possibleConfigurations.get( positionInConfigurationsList );
    }

    public int[][] backupToPreviousConfiguration() {
        if ( previousConfiguration == null )
            return currentConfiguration;
        return previousConfiguration;
    }

    public void moveLeft() {
        if ( COLUMN_ON_TETRIS_BOARD > 0 )
            COLUMN_ON_TETRIS_BOARD--;
    }

    public void moveRight() {
        if ( COLUMN_ON_TETRIS_BOARD < tetrisBoard.getNumberOfColumns() )
            COLUMN_ON_TETRIS_BOARD++;
    }

    public void moveDown() {
        if ( ROW_ON_TETRIS_BOARD < tetrisBoard.getNumberOfRows() )
            ROW_ON_TETRIS_BOARD++;
    }

}

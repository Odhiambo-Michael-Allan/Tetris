package Core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

import Layout.TetrisBoardView;

/**
 * A piece is represented internally by a 5*5 array of integers with 1 indicating
 * a filled position and 0 a blank position...
 */

public abstract class Piece {


    protected ArrayList<int[][]> possibleConfigurations = new ArrayList<>();
    private int positionInConfigurationsList = 0;
    private int[][] previousConfiguration;
    protected int[][] currentConfiguration;
    protected Color pieceColor;  // Every piece has its own color...
    private int PIECE_SIZE = 10;

    private int ROW_ON_TETRIS_BOARD = 0;
    private int COLUMN_ON_TETRIS_BOARD = 9;

    protected TetrisBoardView tetrisBoardView;
    protected ArrayList<PieceListener> listeners = new ArrayList<>();

    protected boolean pieceIsInValidPositionOnBoard = true;

    public void attachBoardView( TetrisBoardView boardView ) {
        this.tetrisBoardView = boardView;
    }

    /**
     * Subclasses must initialize a list of possible configurations...
     */
    protected abstract void initializePossibleConfigurations();

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

    /**
     * Subclasses will override this method to specify a different color...
     */
    protected void setColor() {
        pieceColor = Color.YELLOW;  // The default color is yellow...
    }

    private void drawFilledPositions( GraphicsContext drawingArea ) {
        for ( int row = 0; row < getCurrentConfiguration().length; row++ )
            for ( int col = 0; col < getCurrentConfiguration().length; col++ )
                if ( getCurrentConfiguration()[row][col] == 1 ) {
                    drawingArea.fillRect((col + COLUMN_ON_TETRIS_BOARD) * 10, (row + ROW_ON_TETRIS_BOARD) * 10, PIECE_SIZE, PIECE_SIZE);
                    drawingArea.setStroke( Color.BLACK );
                    drawingArea.strokeRect((col + COLUMN_ON_TETRIS_BOARD) * 10, (row + ROW_ON_TETRIS_BOARD) * 10, PIECE_SIZE, PIECE_SIZE);
                }
    }


    public void proceedToTheNextConfiguration() {
        if ( !pieceIsInValidPositionOnBoard )
            return;
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
        if ( !pieceIsInValidPositionOnBoard )
            return;
        if ( COLUMN_ON_TETRIS_BOARD > 0 )
            COLUMN_ON_TETRIS_BOARD--;
        notifyListeners();
        draw( tetrisBoardView.getGraphicsContext() );
    }

    public void moveRight() {
        if ( !pieceIsInValidPositionOnBoard )
            return;
        if ( COLUMN_ON_TETRIS_BOARD < tetrisBoardView.getNumberOfColumns() )
            COLUMN_ON_TETRIS_BOARD++;
        notifyListeners();
        draw( tetrisBoardView.getGraphicsContext() );
    }

    public void moveDown() {
        ROW_ON_TETRIS_BOARD++;
        if ( pieceHasLanded() || pieceHasLandedOnTopOfAnother() )
            return;
        notifyListeners();
        draw( tetrisBoardView.getGraphicsContext() );
    }

    /**
     * Note that the positions that this method and pieceHasLanded are checking are one row ahead of where
     * the piece currently is. So if they return true, it means that the piece should remain where it is
     * meaning we should decrease the row position i.e ROW_ON_TETRIS_BOARD--...
     * @return
     */
    private boolean pieceHasLanded() {
        int rowOfLastOccupiedTile = getRowOfLastOccupiedTile();
        if ( ROW_ON_TETRIS_BOARD + rowOfLastOccupiedTile < tetrisBoardView.getNumberOfRows() )
            return false;
        ROW_ON_TETRIS_BOARD--;  // Move back to the previous row since this row is invalid...
        tetrisBoardView.markPiecePositionAsOccupied( this );
        pieceIsInValidPositionOnBoard = false;
        tetrisBoardView.addPiece( this );
        return true;
    }

    private boolean pieceHasLandedOnTopOfAnother() {
        for ( int row = 0; row < getCurrentConfiguration().length; row++ )
            for ( int col = 0; col < getCurrentConfiguration().length; col++ )
                if ( getCurrentConfiguration()[row][col] == 1 && row + ROW_ON_TETRIS_BOARD < tetrisBoardView.getNumberOfRows() ) {
                    if ( tetrisBoardView.positionIsOccupied( row + ROW_ON_TETRIS_BOARD, col+COLUMN_ON_TETRIS_BOARD ) ) {
                        System.out.println( "Piece has landed on top of another piece..." );
                        ROW_ON_TETRIS_BOARD--; // Move back to the previous row since this row is invalid...
                        tetrisBoardView.markPiecePositionAsOccupied( this );
                        pieceIsInValidPositionOnBoard = false;
                        tetrisBoardView.addPiece( this );
                        return true;
                    }
                }
        return false;
    }

    private int getRowOfLastOccupiedTile() {
        int rowOfLastOccupiedTile = 0;
        for ( int row = 0; row < getCurrentConfiguration().length; row++ )
            for ( int col = 0; col < getCurrentConfiguration().length; col++ )
                if ( getCurrentConfiguration()[row][col] == 1 )
                    rowOfLastOccupiedTile = row;
        return rowOfLastOccupiedTile;
    }

    public boolean stillInValidPositionOnBoard() {
        return pieceIsInValidPositionOnBoard;
    }

    public boolean positionIsOccupied( int row, int col ) {
        return getCurrentConfiguration()[row][col] == 1;
    }

    public int getRowOnBoard() {
        return ROW_ON_TETRIS_BOARD;
    }

    public int getColumnOnBoard() {
        return COLUMN_ON_TETRIS_BOARD;
    }

    public void register( PieceListener listener ) {
        listeners.add( listener );
    }

    private void notifyListeners() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            PieceListener listener = ( PieceListener ) i.next();
            listener.pieceMoved();
        }
    }

    public void setRowAndColumnOnBoard( int row, int col ) {
        ROW_ON_TETRIS_BOARD = row;
        COLUMN_ON_TETRIS_BOARD = col;
    }

}

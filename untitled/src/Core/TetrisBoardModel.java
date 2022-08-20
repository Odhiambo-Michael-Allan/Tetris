package Core;

import Layout.TetrisBoardView;

import java.util.ArrayList;

public class TetrisBoardModel {

    private int NUMBER_OF_ROWS = 40;
    private int NUMBER_OF_COLUMNS = 20;
    private int numberOfFilledRows = 0;
    private ArrayList<Integer> filledRows = new ArrayList<>();

    private boolean[][] board = new boolean[ NUMBER_OF_ROWS ][ NUMBER_OF_COLUMNS ];
    private TetrisBoardView view; // The view that displays this model...

    public TetrisBoardModel() {}

    public void attachView( TetrisBoardView view ) {
        this.view = view;
    }

    public void markPiecePositionAsEmpty( Piece piece ) {
        for ( int row = 0; row < piece.getCurrentConfiguration().length; row++ ) {
            for ( int col = 0; col < piece.getCurrentConfiguration()[0].length; col++ ) {
                if ( piece.positionIsOccupied( row, col ) ) {
                    markBoardPositionAsEmpty( row+piece.getRowOnBoard(), col+piece.getColumnOnBoard() );
                }
            }
        }
    }

    private void markBoardPositionAsEmpty( int row, int col ) {
        board[ row ][ col ] = false;
    }

    /**
     * The board is instructed to mark a piece position as occupied only when a piece has landed. We
     * can take this chance to verify whether there are any filled rows that need clearing...
     */
    public void markPiecePositionAsOccupied( Piece piece ) {
        markPositionAsOccupied( piece );
        checkForFilledRows();
        if ( filledRowsExist() ) {
            notifyPiecesOnTheFilledRowsToClearThemselves();
            markPreviouslyFilledRowsAsEmpty();
            view.instructPiecesToShiftDownBy( filledRows.size() );
            filledRows.clear();  // Don't forget to clear this list...
            numberOfFilledRows = 0;
        }
    }

    public void markPieceUpdatedPositionAsOccupied( Piece piece ) {
        markPositionAsOccupied( piece );
    }

    public void markPositionAsOccupied( Piece piece ) {
        for ( int row = 0; row < piece.getCurrentConfiguration().length; row++ ) {
            for ( int col = 0; col < piece.getCurrentConfiguration()[0].length; col++ ) {
                if ( piece.positionIsOccupied( row, col ) ) {
                    System.out.println( row + ", " + col );
                    markBoardPositionAsOccupied( row+piece.getRowOnBoard(), col+piece.getColumnOnBoard() );
                }
            }
        }
    }

    private void markBoardPositionAsOccupied( int row, int col ) {
        board[ row ][ col ] = true;
    }

    private void checkForFilledRows() {
        // We start checking from the last row going upwards...
        for ( int row = NUMBER_OF_ROWS - 1; row >= 0; row-- ) {
            boolean rowIsFilled = true;
            for ( int col = 0; col < NUMBER_OF_COLUMNS; col++ ) {
                if ( board[ row ][ col ] == false )
                    rowIsFilled = false;
            }
            if ( rowIsFilled ) {
                filledRows.add( row );
                numberOfFilledRows++;
            }
        }
        System.out.println( String.format( "There are currently %d filled rows..", numberOfFilledRows ) );
    }

    private boolean filledRowsExist() {
        return filledRows.size() > 0;
    }

    private void notifyPiecesOnTheFilledRowsToClearThemselves() {
        view.notifyPiecesToRemoveThemselvesFromTheSpecifiedRows( filledRows );
    }

    private void markPreviouslyFilledRowsAsEmpty() {
        for ( Integer row : filledRows )
            for ( int col = 0; col < NUMBER_OF_COLUMNS; col++ )
                board[ row ][ col ] = false;
    }

    public boolean positionIsOccupied( int row, int col ) {
        return board[ row ][ col ] == true;
    }
}

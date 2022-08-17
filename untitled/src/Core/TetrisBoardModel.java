package Core;

public class TetrisBoardModel {

    private int NUMBER_OF_ROWS = 40;
    private int NUMBER_OF_COLUMNS = 20;

    private boolean[][] board = new boolean[ NUMBER_OF_ROWS ][ NUMBER_OF_COLUMNS ];

    public TetrisBoardModel() {}

    public void markPiecePositionAsOccupied( Piece piece ) {
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

    public boolean positionIsOccupied( int row, int col ) {
        return board[ row ][ col ] == true;
    }
}

package Core;

import Layout.TetrisBoard;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class O extends Piece {

    private int[][] configuration1 = new int[][] {
            { 1, 1, 0, 0, 0 },
            { 1, 1, 0, 0, 0 }
    };

    public O( TetrisBoard board ) {
        initializeTetrisBoard( board );
        initializePossibleConfigurations();
    }

    @Override
    protected void initializePossibleConfigurations() {
        possibleConfigurations = new ArrayList<>();
        possibleConfigurations.add( configuration1 );
        currentConfiguration = possibleConfigurations.get( 0 );
    }

    @Override
    protected void initializeTetrisBoard( TetrisBoard board ) {
        tetrisBoard = board;
    }

    protected void setColor() {
        pieceColor = Color.BLUE;
    }
}

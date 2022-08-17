package Core;

import Layout.TetrisBoardView;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class O extends Piece {

    private int[][] configuration1 = new int[][] {
            { 1, 1, 0, 0, 0 },
            { 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public O() {
        initializePossibleConfigurations();
    }

    @Override
    protected void initializePossibleConfigurations() {
        possibleConfigurations = new ArrayList<>();
        possibleConfigurations.add( configuration1 );
        currentConfiguration = possibleConfigurations.get( 0 );
    }

    protected void setColor() {
        pieceColor = Color.BLUE;
    }
}

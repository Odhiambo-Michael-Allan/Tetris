package Core;

import java.util.ArrayList;

import Layout.TetrisBoardView;

public class I extends Piece {

    private int[][] configuration1 = {
            { 1, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private int[][] configuration2 = {
            { 1, 1, 1, 1, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public I() {
        initializePossibleConfigurations();
    }

    @Override
    protected void initializePossibleConfigurations() {
        possibleConfigurations.add( configuration1 );
        possibleConfigurations.add( configuration2 );
        currentConfiguration = possibleConfigurations.get( 0 );
    }

}

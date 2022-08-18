package Core;

import javafx.scene.paint.Color;

public class J extends Piece {

    int[][] configuration1 = {
            { 1, 1, 0, 0, 0 },
            { 1, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    int[][] configuration2 = {
            { 1, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    int[][] configuration3 = {
            { 0, 1, 0, 0, 0 },
            { 0, 1, 0, 0, 0 },
            { 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    int[][] configuration4 = {
            { 1, 0, 0, 0, 0 },
            { 1, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public J() {
        initializePossibleConfigurations();
    }
    @Override
    protected void initializePossibleConfigurations() {
        possibleConfigurations.add( configuration1 );
        possibleConfigurations.add( configuration2 );
        possibleConfigurations.add( configuration3 );
        possibleConfigurations.add( configuration4 );
        currentConfiguration = possibleConfigurations.get( 0 );
    }

    protected void setColor() {
        pieceColor = Color.BEIGE;
    }
}

package Core;

import javafx.scene.paint.Color;

public class T extends Piece {

    private int[][] configuration1 = {
            { 0, 1, 0, 0, 0 },
            { 1, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private int[][] configuration2 = {
            { 1, 0, 0, 0, 0 },
            { 1, 1, 0, 0, 0 },
            { 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private int[][] configuration3 = {
            { 1, 1, 1, 0, 0 },
            { 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private int[][] configuration4 = {
            { 0, 1, 0, 0, 0 },
            { 1, 1, 0, 0, 0 },
            { 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public T() {
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
        pieceColor = Color.FIREBRICK;
    }
}

package Core;

import javafx.scene.paint.Color;

public class S extends Piece {

    private int[][] configuration1 = {
            { 0, 1, 1, 0, 0 },
            { 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private int[][] configuration2 = {
            { 1, 0, 0, 0, 0 },
            { 1, 1, 0, 0, 0 },
            { 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public S() {
        initializePossibleConfigurations();
    }
    @Override
    protected void initializePossibleConfigurations() {
        possibleConfigurations.add( configuration1 );
        possibleConfigurations.add( configuration2 );
        currentConfiguration = possibleConfigurations.get( 0 );
    }

    protected void setColor() {
        pieceColor = Color.PURPLE;
    }
}

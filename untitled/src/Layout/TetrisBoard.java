package Layout;

import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import Core.Piece;
import Core.I;
import Core.O;

public class TetrisBoard extends Pane {

    private Canvas canvas = new Canvas( 200, 400 );
    private GraphicsContext drawingArea = canvas.getGraphicsContext2D();
    private final int NUMBER_OF_ROWS = 40;
    private final int NUMBER_OF_COLUMNS = 20;

    public TetrisBoard() {
        super.getChildren().addAll( canvas );
        fillDrawingArea();
    }

    private void fillDrawingArea() {
        fillCanvas();
        drawGrid();
        Piece i = new I( this );
        Piece o = new O( this );
        i.proceedToTheNextConfiguration();


        for ( int j = 0; j < 5; j++ ) {
            i.moveLeft();
            i.moveDown();
        }

        i.draw( drawingArea );
        o.draw( drawingArea );
    }

    private void fillCanvas() {
        drawingArea.setFill( Color.GRAY );
        drawingArea.fillRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
    }

    private void drawGrid() {
        drawingArea.setFill( Color.BLACK );
        drawingArea.setLineWidth( 0.2 );
        for ( int row = 0; row < NUMBER_OF_ROWS; row++ )  // Draw the rows...
            drawingArea.strokeLine( 0, row*10, canvas.getWidth(), row*10 );
        for ( int col = 0; col < NUMBER_OF_COLUMNS; col++ )  // Draw the columns...
            drawingArea.strokeLine( col*10, 0, col*10, canvas.getHeight() );
    }

    public void getInPosition() {
        relocate( 150, 50 );
    }

    public int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }
}

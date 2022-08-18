package Layout;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import Core.Piece;

public class NextPiecePane extends Pane {

    private Canvas canvas = new Canvas( 100, 100 );
    private Label next = new Label( "NEXT" );
    private GraphicsContext drawingArea = canvas.getGraphicsContext2D();

    public NextPiecePane() {
        super.getChildren().addAll( next, canvas );
        next.setTextFill( Color.WHITE );
        next.relocate( 30, 0 );
        canvas.relocate( 0, 20 );
        fillDrawingArea();
    }

    private void fillDrawingArea() {
        drawingArea.setFill( Color.GRAY );
        drawingArea.fillRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
    }

    public void getInPosition() {
        relocate( 380, 100 );
    }

    public void display( Piece nextPiece ) {
        fillDrawingArea();
        nextPiece.draw( drawingArea );
    }
}

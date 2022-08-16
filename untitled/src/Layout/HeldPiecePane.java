package Layout;

import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class HeldPiecePane extends Pane {

    private Canvas canvas = new Canvas( 100, 100 );
    Label held = new Label( "Held" );
    private GraphicsContext drawingArea = canvas.getGraphicsContext2D();

    public HeldPiecePane() {
        super.getChildren().addAll( held, canvas );
        held.setTextFill( Color.WHITE );
        held.relocate( 30, 0 );
        canvas.relocate( 0, 20 );
        fillDrawingArea();
    }

    private void fillDrawingArea() {
        drawingArea.setFill(Color.GRAY );
        drawingArea.fillRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
    }

    public void getInPosition() {
        relocate( 20, 100 );
    }
}

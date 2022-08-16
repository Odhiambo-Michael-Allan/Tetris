package Layout;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ScorePane extends Pane {

    private Label score = new Label( String.format( "SCORE: %d", 0 ) );

    public ScorePane() {
        super.getChildren().addAll( score );
        score.setTextFill( Color.WHITE );
        score.relocate( 10, 10 );
    }

    public void getInPosition() {
        relocate( 200, 10 );
    }
}

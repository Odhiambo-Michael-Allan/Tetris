package Layout;

import javafx.scene.layout.Pane;

public class MainPane extends Pane {

    TetrisBoard mainBoard = new TetrisBoard();
    HeldPiecePane heldPiecePane = new HeldPiecePane();
    NextPiecePane nextPiecePane = new NextPiecePane();
    ScorePane scorePane = new ScorePane();

    public MainPane() {
        super.getChildren().addAll( mainBoard, heldPiecePane, nextPiecePane, scorePane );
        mainBoard.getInPosition();
        nextPiecePane.getInPosition();
        heldPiecePane.getInPosition();
        scorePane.getInPosition();
        super.setPrefSize( 500, 500 );
        setStyle( "-fx-background-color:rgb( 3, 54, 73 );");
    }

}

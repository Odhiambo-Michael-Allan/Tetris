package Layout;

import Core.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class MainPane extends Pane implements ThreadListener {

    TetrisBoardModel tetrisBoardModel = new TetrisBoardModel();
    TetrisBoardView tetrisBoardView = new TetrisBoardView(tetrisBoardModel);
    TetrisBoardController controller = new TetrisBoardController(tetrisBoardView);
    HeldPiecePane heldPiecePane = new HeldPiecePane();
    NextPiecePane nextPiecePane = new NextPiecePane();
    ScorePane scorePane = new ScorePane();

    Piece currentPiece;
    Piece nextPiece;

    public MainPane() {
        super.getChildren().addAll(tetrisBoardView, heldPiecePane, nextPiecePane, scorePane );
        super.setPrefSize( 500, 500 );
        tetrisBoardModel.attachView( tetrisBoardView );
        tetrisBoardView.getInPosition();
        tetrisBoardView.attachController( controller );
        nextPiecePane.getInPosition();
        heldPiecePane.getInPosition();
        scorePane.getInPosition();
        setStyle( "-fx-background-color:rgb( 3, 54, 73 );");
    }


    public void launchGame() {
        currentPiece = PieceFactory.getPiece();
        currentPiece.register(tetrisBoardView);
        currentPiece.attachBoardView(tetrisBoardView);
        tetrisBoardView.setCurrentlyMovingPiece( currentPiece );  // This will enable the board to communicate with the currently moving piece...
        nextPiece = PieceFactory.getPiece();
        nextPiece.register(tetrisBoardView);
        nextPiece.attachBoardView(tetrisBoardView);
        nextPiece.setRowAndColumnOnBoard( 3, 4 );
        nextPiecePane.display( nextPiece );
        PieceMovingThread thread = new PieceMovingThread( currentPiece );
        thread.register( this );
        thread.start();
    }

    @Override
    public void doneAnimating() {
        currentPiece = nextPiece;
        currentPiece.setRowAndColumnOnBoard( 0, 9 );
        tetrisBoardView.setCurrentlyMovingPiece( currentPiece );
        nextPiece = PieceFactory.getPiece();
        nextPiece.register(tetrisBoardView);
        nextPiece.attachBoardView(tetrisBoardView);
        nextPiece.setRowAndColumnOnBoard( 3, 4 );
        nextPiecePane.display( nextPiece );
        PieceMovingThread thread = new PieceMovingThread( currentPiece );
        thread.register( this );
        thread.start();
    }

    public void keyPressed( KeyEvent event ) {
        controller.keyPressed( event );
    }
}

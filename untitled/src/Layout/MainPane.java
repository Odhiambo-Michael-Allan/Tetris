package Layout;

import Core.*;
import javafx.scene.layout.Pane;

public class MainPane extends Pane implements ThreadListener {

    TetrisBoardView mainBoardView = new TetrisBoardView( new TetrisBoardModel() );
    HeldPiecePane heldPiecePane = new HeldPiecePane();
    NextPiecePane nextPiecePane = new NextPiecePane();
    ScorePane scorePane = new ScorePane();

    Piece currentPiece;
    Piece nextPiece;

    public MainPane() {
        super.getChildren().addAll( mainBoardView, heldPiecePane, nextPiecePane, scorePane );
        mainBoardView.getInPosition();
        nextPiecePane.getInPosition();
        heldPiecePane.getInPosition();
        scorePane.getInPosition();
        super.setPrefSize( 500, 500 );
        setStyle( "-fx-background-color:rgb( 3, 54, 73 );");
    }

    public TetrisBoardView getTetrisBoardView() {
        return mainBoardView;
    }

    public void launchGame() {
        currentPiece = PieceFactory.getPiece();
        currentPiece.register( mainBoardView );
        currentPiece.attachBoardView( mainBoardView );
        nextPiece = PieceFactory.getPiece();
        nextPiece.register( mainBoardView );
        nextPiece.attachBoardView( mainBoardView );
        PieceMovingThread thread = new PieceMovingThread( currentPiece );
        thread.register( this );
        thread.start();
    }

    @Override
    public void doneAnimating() {
        currentPiece = nextPiece;
        nextPiece = PieceFactory.getPiece();
        nextPiece.register( mainBoardView );
        nextPiece.attachBoardView( mainBoardView );
        PieceMovingThread thread = new PieceMovingThread( currentPiece );
        thread.register( this );
        thread.start();
    }
}

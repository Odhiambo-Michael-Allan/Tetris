package Layout;

import Core.*;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

public class TetrisBoardView extends Pane implements PieceListener {

    private Canvas canvas = new Canvas( 200, 400 );
    private GraphicsContext drawingArea = canvas.getGraphicsContext2D();
    private final int NUMBER_OF_ROWS = 40;
    private final int NUMBER_OF_COLUMNS = 20;

    private TetrisBoardController controller;

    private ArrayList<Piece> piecesCurrentlyOnTheBoard = new ArrayList<>();;

    private TetrisBoardModel model;

    private Piece currentlyMovingPiece;

    public TetrisBoardView( TetrisBoardModel model ) {
        super.getChildren().addAll( canvas );
        this.model = model;
        draw();
    }

    public void attachController( TetrisBoardController controller ) {
        this.controller = controller;
    }

    public void setCurrentlyMovingPiece( Piece piece ) {
        this.currentlyMovingPiece = piece;
    }

    public Piece getCurrentlyMovingPiece() {
        return this.currentlyMovingPiece;
    }

    public void draw() {
        fillDrawingArea();
        drawPieces();
    }

    public void drawPieces() {
        Iterator i = piecesCurrentlyOnTheBoard.iterator();
        while ( i.hasNext() ) {
            Piece piece = ( Piece ) i.next();
            piece.draw( drawingArea );
        }
    }



    private void fillDrawingArea() {
        fillCanvas();
        drawGrid();
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

    public void addPiece( Piece piece ) {
        piecesCurrentlyOnTheBoard.add( piece );
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

    @Override
    public void pieceMoved() {
        draw();
    }

    public GraphicsContext getGraphicsContext() {
        return drawingArea;
    }

    public void markPiecePositionAsOccupied( Piece piece ) {
        model.markPiecePositionAsOccupied( piece );
    }

    public boolean positionIsOccupied( int row, int col ) {
        return model.positionIsOccupied( row, col );
    }


}

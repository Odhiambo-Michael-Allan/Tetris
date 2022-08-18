package Core;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import Layout.*;

public class TetrisBoardController {

    TetrisBoardView view;

    public TetrisBoardController( TetrisBoardView view ) {
        this.view = view;
    }


    public void keyPressed( KeyEvent event ) {
        KeyCode key = event.getCode();
        System.out.println( key + " Pressed..." );
        switch ( key ) {
            case C :
                view.getCurrentlyMovingPiece().proceedToTheNextConfiguration();
                break;
            case LEFT :
                view.getCurrentlyMovingPiece().moveLeft();
                break;
            case RIGHT :
                view.getCurrentlyMovingPiece().moveRight();
                break;
        }
    }
}

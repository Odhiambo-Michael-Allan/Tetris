package Core;

import java.util.ArrayList;
import java.util.Iterator;

public class PieceMovingThread extends Thread {

    private Piece piece;
    private ArrayList<ThreadListener> listeners;

    public PieceMovingThread( Piece piece ) {
        setDaemon( true );
        this.piece = piece;
        listeners = new ArrayList<>();
    }

    public void run() {
        while ( piece.stillInValidPositionOnBoard() ) {
            piece.moveDown();
            try {
                Thread.sleep( 500 );
            } catch ( InterruptedException e ) {}
        }
        notifyListenersImDoneAnimating();
        System.out.println( "Piece moving thread OUTTT..." );
    }

    public void register( ThreadListener listener ) {
        listeners.add( listener );
    }

    private void notifyListenersImDoneAnimating() {
        Iterator i = listeners.iterator();
        while ( i.hasNext() ) {
            ThreadListener listener = ( ThreadListener ) i.next();
            listener.doneAnimating();
        }

    }


}

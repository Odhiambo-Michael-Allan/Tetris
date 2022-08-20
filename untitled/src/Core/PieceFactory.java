package Core;

import java.util.Random;

public class PieceFactory {

    public static Piece getPiece() {
        Piece chosenPiece = new I();
        int piece = new Random().nextInt( 7 );
        switch ( piece ) {
            case 0 :
                chosenPiece = new I();
                break;
            case 1 :
                chosenPiece = new O();
                break;
            case 2 :
                chosenPiece = new J();
                break;
            case 3 :
                chosenPiece = new S();
                break;
            case 4 :
                chosenPiece = new Z();
                break;
            case 5:
                chosenPiece = new T();
                break;
            case 6 :
                chosenPiece = new L();
                break;
        }
        return new I();

    }
}

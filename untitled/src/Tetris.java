
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import Layout.MainPane;

public class Tetris extends Application {

    public static void main( String[] args ) {
        launch( args );
    }

    public void start( Stage stage ) {

        MainPane mainPane = new MainPane();
        Scene scene = new Scene( mainPane );
        scene.setOnKeyPressed( event -> {
            System.out.println( "Key pressed..." );
            mainPane.keyPressed( event );
        } );
        stage.setScene( scene );
        stage.setTitle( "Tetris 1.0" );
        stage.show();

        mainPane.launchGame();
    }
}

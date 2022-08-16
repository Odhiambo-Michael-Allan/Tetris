
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import Layout.MainPane;

public class Tetris extends Application {

    public static void main( String[] args ) {
        launch( args );
    }

    public void start( Stage stage ) {

        Pane mainPane = new MainPane();
        stage.setScene( new Scene( mainPane ) );
        stage.setTitle( "Tetris 1.0" );
        stage.show();
    }
}

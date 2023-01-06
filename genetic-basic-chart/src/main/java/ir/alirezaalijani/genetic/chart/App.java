package ir.alirezaalijani.genetic.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 *
 */
@Slf4j
public class App extends Application {
    public static void main( String[] args ){
        log.info( "Hello Genetic Gui!" );
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getClassLoader().getResource("view/app.fxml");
        System.out.println(resource);
        if (resource!=null){
            Parent root = FXMLLoader.load(resource);
            primaryStage.setTitle("Genetic");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }
}

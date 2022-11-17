import Logger.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        Application.launch(args);
        Log.setupLogger(logger);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        Scene scene = new Scene(root,800,600);
        stage.setTitle("Магазин амуніції");
        stage.setScene(scene);
        stage.show();
    }
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("scenes/start.fxml"));
        Scene startup = new Scene(root, 640, 520);
        startup.getStylesheets().add(getClass().getResource("./style/main.css").toExternalForm());

        window.getIcons().add(new Image(this.getClass().getResourceAsStream("assets/fire.png")));
        window.setTitle("Mortem");
        window.setResizable(false);
        window.setScene(startup);
        window.show();
    }
}

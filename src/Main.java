import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static int WIDTH = 640;
    public static int HEIGHT = 520;


    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        window.getIcons().add(new Image(this.getClass().getResourceAsStream("assets/fire.png")));
        window.setTitle("Mortem");
        window.setResizable(false);
        window.setScene(startup());
        window.show();
    }



    public Scene startup() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("scenes/start.fxml"));
        Scene startup = new Scene(root, WIDTH, HEIGHT);
        startup.getStylesheets().add(getClass().getResource("style/start.css").toExternalForm());

        return  startup;
    }
}

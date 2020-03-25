import Game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scenes.Context;

import java.io.IOException;

public class Main extends Application {

    public static int WIDTH = 640;
    public static int HEIGHT = 520;

    private Stage window;
    Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        game = Context.getInstance().getGame();

        window.getIcons().add(new Image(this.getClass().getResourceAsStream("assets/fire.png")));
        window.setTitle("Mortem");
        window.setResizable(false);
        window.setScene(startup());
        window.show();
    }

    public Scene startup() throws IOException {
        Parent root = null;

        if (game.getHero().getName().isEmpty()) {
            root = FXMLLoader.load(getClass().getResource("scenes/start.fxml"));
        } else if (!game.getHero().getName().isEmpty()) {
            root = FXMLLoader.load(getClass().getResource("scenes/root.fxml"));
        }
        Scene startup = new Scene(root, WIDTH, HEIGHT);
        return startup;
    }
}

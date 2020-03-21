package scenes;

import Game.Game;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRoot implements Initializable {
    Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = Context.getInstance().getGame();

        System.out.println(game.getName());

    }
}

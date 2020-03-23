package scenes;

import Game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRoot implements Initializable {

    Game game;
    @FXML
    TextArea mainText;
    @FXML
    TextField input;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = Context.getInstance().getGame();
        mainText.appendText(game.getName());

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                input.requestFocus();
            }
        });
    }

    public void onEnter(ActionEvent e) {
        mainText.appendText("\n> " + input.getText());
        input.clear();
    }

}

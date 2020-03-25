package scenes;

import Game.Game;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRoot implements Initializable {

    int index = 0;
    Game game;
    @FXML
    TextArea mainText;
    @FXML
    TextField input;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = Context.getInstance().getGame();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                input.requestFocus();
                setNarratorText();
            }
        });
    }

    private synchronized void setNarratorText() {
        for (index = 0; index < 3; index++) {
            mainText.appendText("> " + game.getMainText(index) + "\n");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void typing(String text, int time) {
        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(time));
            }

            @Override
            protected void interpolate(double frac) {
                int len = text.length();
                int n = Math.round(len * (float) frac);
                mainText.appendText(text.charAt(n) + "");

                if (n >= text.length()) this.stop();
            }
        };
        animation.play();
    }

    public void onEnter(ActionEvent e) {
        mainText.appendText("\n>> " + game.getCommandText(input.getText()));
        input.clear();
    }

}

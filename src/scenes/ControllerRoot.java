package scenes;

import Game.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ControllerRoot implements Initializable {

    Game game;
    @FXML
    TextArea mainText;
    @FXML
    TextField input;
    @FXML
    VBox sideBar;
    @FXML
    GridPane gridMap;
    @FXML
    Label health;
    @FXML
    Label stamina;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = Context.getInstance().getGame();


        Platform.runLater(() -> {
            input.setDisable(true);
            setNarratorText();
            sideBarUpdate();
        });
    }

    private void sideBarUpdate() {
        gridMap.getChildren().clear();

        game.getMaze().updateMap(game.getHero().getX(), game.getHero().getY());
        game.getMaze().fillGrid(gridMap, game.getHero().getX(), game.getHero().getY());
        health.setText("Health: " + game.getHero().getHealth() + " / " + game.getHero().getMaxHealth());
        stamina.setText("Stamina: " + game.getHero().getStamina() + " / " + game.getHero().getMaxStamina());
    }

    private synchronized void setNarratorText() {

        AtomicInteger index = new AtomicInteger(0);
        Timeline setText = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            mainText.appendText("> " + game.getMainText(index.get()) + "\n");
            index.getAndIncrement();
        }));
        setText.setCycleCount(3);
        setText.play();

        setText.setOnFinished(e -> {
            input.setDisable(false);
            input.requestFocus();
            runGame();
        });
    }

    private void runGame() {
        int path = game.getMaze().getPath(game.getHero().getX(), game.getHero().getY());
        mainText.appendText("\n> Możliwa ilość dróg do wyboru: [" + path + "]. Aby przejść dalej, wpisz: \n");

        ArrayList<String> ways = game.getMaze().getDir(game.getHero().getX(), game.getHero().getY());
        game.getHero().addDir(ways);
        for (int i = 0; i < ways.size(); i++) {
            mainText.appendText("> [" + i + "] " + ways.get(i) + "\n");
        }
    }

    ////typing function in controllerStart.java

    public void onEnter(ActionEvent e) throws Exception {
        mainText.appendText("\n>> " + input.getText());
        mainText.appendText("\n>> " + game.getCommandText(input.getText()));
        input.clear();

        if (game.move()) game.room(mainText);
        sideBarUpdate();
        runGame();
    }


    /// getters
}

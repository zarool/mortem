package scenes;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField input;
    @FXML
    Text text;
    @FXML
    Button loginBtn;
    String content = "Budzisz się z głębokiego snu, przypominając sobie swoje imię...";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typing();
    }


    public void typing() {
        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(7000));
            }

            @Override
            protected void interpolate(double frac) {
                int len = content.length();
                int n = Math.round(len * (float) frac);
                text.setText(content.substring(0, n));

                if (text.getText().length() == content.length()) {
                    input.setDisable(false);
                }
            }
        };

        animation.play();

    }

    public void loginBtnClicked() throws IOException {

        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("./root.fxml"));
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader);
        stage.setScene(scene);
    }
}
package Game;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Room {

    int index;
    TextArea mainText;

    Room(TextArea mainText) {

        this.mainText = mainText;

        float choice = (float) Math.random();
        /////50% monster room, 10% chest room, 40% free room
        if (choice < 0.5) {
            index = 0;
        } else if (choice < 0.9) {
            index = 1;
        } else {
            index = 2;
        }
    }

    private void monsterRoom(Hero hero) {
        int number = (int) Math.round(Math.random() * hero.getLevel());
        number = Math.max(1, number);

        printText("Atakuje cię" + number + "potwór");

        TextField a = new TextField();

        a.setOnAction(actionEvent -> {

        });
        ArrayList<String> b = new ArrayList<>();
        b.add("atak");
        b.add("seppuku");
        b.add("wyposazenie");
        hero.addDir(b);
    }

    private void emptyRoom(Hero hero) {
        printText(hero.getName());
    }


    private void chestRoom(Hero hero) {
        printText(hero.getName());
    }

    public void goToRoom(Hero hero) {
        switch (index) {
            case 0:
                monsterRoom(hero);
                break;
            case 1:
                emptyRoom(hero);
                break;
            case 2:
                chestRoom(hero);
                break;
        }

    }

    private void printText(String text) {
        mainText.appendText("\n> " + text);
    }
}

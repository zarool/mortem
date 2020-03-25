package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private Hero hero;
    private ArrayList<String> commandNames;
    private ArrayList<String> commandTexts;
    private ArrayList<String> mainTexts;

    public Game() {
        hero = new Hero();
        commandNames = new ArrayList<String>();
        commandTexts = new ArrayList<String>();
        mainTexts = new ArrayList<String>();
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() throws IOException {
        File data = new File("./src/Game/Command.txt");
        BufferedReader br = new BufferedReader(new FileReader(data));

        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            String[] split = currentLine.split(";");
            commandNames.add(split[0]);
            commandTexts.add(split[1]);
        }

        File dataText = new File("./src/Game/MainText.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(dataText));
        String line;
        while ((line = br2.readLine()) != null) {
            String[] split = line.split(";");
            mainTexts.add(split[0]);
        }

    }

    /////getters and setters
    public Hero getHero() {
        return hero;
    }

    public String getMainText(int index) {
        return mainTexts.get(index);
    }

    public String getCommandText(String input) {
        int index = 0;

        for (int i = 0; i < commandNames.size(); i++) {
            if (commandNames.get(i).equals(input)) index = i;
        }
        return commandTexts.get(index);
    }
}

package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Game {


    //objects
    private Hero hero;
    private Maze maze;
    private Command command;
    private Room room;

    //data
    private ArrayList<String> commandTitles;
    private ArrayList<String> commandTexts;
    private ArrayList<String> commandMethod;
    private ArrayList<String> mainTexts;

    //variables
    boolean isMove = false;

    public Game() {
        hero = new Hero(1, 1);
        maze = new Maze();
        command = new Command(this.hero);

        commandTitles = new ArrayList<String>();
        commandTexts = new ArrayList<String>();
        commandMethod = new ArrayList<String>();
        mainTexts = new ArrayList<String>();

        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        generateMaze();
    }

    public void generateMaze() {
        maze.run();
        maze.updateMap(getHero().getX(), getHero().getY());
    }

    private void readData() throws IOException {
        File data = new File("./src/assets/data/Command.txt");
        BufferedReader br = new BufferedReader(new FileReader(data));

        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            String[] split = currentLine.split(";");
            commandTitles.add(split[0]);
            commandTexts.add(split[1]);
            commandMethod.add(split[0]);
        }

        File dataText = new File("./src/assets/data/MainText.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(dataText));
        String line;
        while ((line = br2.readLine()) != null) {
            String[] split = line.split(";");
            mainTexts.add(split[0]);
        }

        String a = commandTexts.get(7).replaceAll("#", "\n");
        commandTexts.set(7, a);
    }


    public void room() {
        room.generateRoom();

        isMove = false;
    }

    /////getters and setters
    public Hero getHero() {
        return hero;
    }

    public Maze getMaze() {
        return maze;
    }

    public String getMainText(int index) {
        return mainTexts.get(index);
    }

    public String getCommandText(String input) throws Exception {
        int index = 0;

        for (int i = 0; i < commandTitles.size(); i++) {
            if (commandTitles.get(i).equals(input)) index = i;
        }

        if (0 < index && index < 5) isMove = true;
        Method method = Command.class.getMethod(commandTitles.get(index), String.class);
        return (String) method.invoke(command, commandTexts.get(index));
    }


    public ArrayList<String> getMap() {
        return maze.getMap(hero.getX(), hero.getY());
    }

    public boolean move() {
        return isMove;
    }

}

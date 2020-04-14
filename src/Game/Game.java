package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private Hero hero;
    private Maze maze;
    private ArrayList<String> commandTitles;
    private ArrayList<String> commandTexts;
    private ArrayList<String> mainTexts;
    private ArrayList<String> dir;

    ///top, right, bottom, left

    public Game() {
        hero = new Hero(1, 1);
        maze = new Maze();
        commandTitles = new ArrayList<String>();
        commandTexts = new ArrayList<String>();
        mainTexts = new ArrayList<String>();
        dir = new ArrayList<String>();

        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        generateMaze();
        //System.out.println(maze.getGrid());
    }

    public void generateMaze() {
        maze.run();
        maze.updateMap(getHero().getX(), getHero().getY());
    }

    private void readData() throws IOException {
        File data = new File("./src/Game/Command.txt");
        BufferedReader br = new BufferedReader(new FileReader(data));

        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            String[] split = currentLine.split(";");
            commandTitles.add(split[0]);
            commandTexts.add(split[1]);
        }

        File dataText = new File("./src/Game/MainText.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(dataText));
        String line;
        while ((line = br2.readLine()) != null) {
            String[] split = line.split(";");
            mainTexts.add(split[0]);
        }

        String a = commandTexts.get(7).replaceAll("#", "\n");
        commandTexts.set(7, a);
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

    public String getCommandText(String input) {
        int index = 0;

        for (int i = 0; i < commandTitles.size(); i++) {
            if (commandTitles.get(i).equals(input)) index = i;
        }
        System.out.println(index);
        if (checkIfMove(input) || !(0 <= index && index <= 4)) return commandTexts.get(index);
        else return commandTexts.get(0);

    }

    public void checkDir(ArrayList<String> words) {
        for (String w : words) {
            dir.add(w);
        }
    }

    private boolean checkIfMove(String input) {
        for (String dir : this.dir) {
            if (dir.equals(input)) {
                makeMove(input);
                return true;
            }
        }
        return false;
    }

    private void makeMove(String input) {
        if (input.equals("gora")) {
            hero.setY(hero.getY() - 1);
            this.dir.clear();
        } else if (input.equals("prawo")) {
            hero.setX(hero.getX() + 1);
            this.dir.clear();
        } else if (input.equals("dol")) {
            hero.setY(hero.getY() + 1);
            this.dir.clear();
        } else if (input.equals("lewo")) {
            hero.setX(hero.getX() - 1);
            this.dir.clear();
        }
        maze.updateMap(hero.getX(), hero.getY());
    }

    public ArrayList<String> getMap() {
        return maze.getMap(hero.getX(), hero.getY());
    }
}

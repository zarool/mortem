package Game;

import java.io.*;
import java.util.ArrayList;

public class Hero {

    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private int stamina;
    private int maxStamina;
    private int x;
    private int y;
    public ArrayList<String> dir;


    Hero(int x, int y) {
        this.x = x;
        this.y = y;
        dir = new ArrayList<String>();

        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() throws IOException {
        File data = new File("./src/assets/data/heroData.txt");
        BufferedReader br = new BufferedReader(new FileReader(data));

        String all = br.readLine();
        if (all == null) {
            this.name = "";
            this.level = 1;
            this.health = 10;
            this.maxHealth = this.health;
            this.stamina = 8;
            this.maxStamina = this.stamina;
        } else {
            String[] dataText;
            dataText = all.split(";");
            this.name = dataText[0];
            this.level = Integer.valueOf(dataText[1]);
            this.health = Integer.valueOf(dataText[2]);
            this.maxHealth = Integer.valueOf(dataText[3]);
            this.stamina = Integer.valueOf(dataText[4]);
            this.maxStamina = Integer.valueOf(dataText[5]);
        }

    }

    public void addDir(ArrayList<String> words) {
        for (String w : words) {
            dir.add(w);
        }
    }

    public boolean hasDir(String input) {
        for (String d : dir) {
            if (input.equals(d)) {
                this.dir.clear();
                return true;
            }
        }
        return false;
    }


    /////getters and setters
    public String toString() {
        return this.name + " ma: " + this.level + " lvl | " + this.health + " hp | " + this.stamina + " sp | X: " + this.x + " | Y: " + this.y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        File file = new File("./src/assets/data/heroData.txt");
        FileWriter input = null;

        try {
            input = new FileWriter(file);
            input.write(name + ";" + this.level + ";" + this.health + ";"
                    + this.maxHealth + ";" + this.stamina + ";" + this.maxStamina);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getStamina() {
        return this.stamina;
    }

    public int getMaxStamina() {
        return this.maxStamina;
    }

    public int getLevel() {
        return this.level;
    }

    public void gameOver() {
        health = 0;
    }
}

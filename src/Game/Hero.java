package Game;

import java.io.*;

public class Hero {

    private String name;
    private int level;
    private int health;
    private int stamina;


    Hero() {
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() throws IOException {
        File data = new File("./src/Game/heroData.txt");
        BufferedReader br = new BufferedReader(new FileReader(data));

        String all = br.readLine();
        if (all == null) {
            this.name = "";
            this.level = 1;
            this.health = 10;
            this.stamina = 8;
        } else {
            String[] dataText;
            dataText = all.split(";");
            this.name = dataText[0];
            this.level = Integer.valueOf(dataText[1]);
            this.health = Integer.valueOf(dataText[2]);
            this.stamina = Integer.valueOf(dataText[3]);
        }

    }


    /////getters and setters
    public String toString() {
        return this.name + " ma: " + this.level + " lvl | " + this.health + " hp | " + this.stamina + " sp |";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        File file = new File("./src/Game/heroData.txt");
        FileWriter input = null;

        try {
            input = new FileWriter(file);
            input.write(name + ";" + this.level + ";" + this.health + ";" + this.stamina);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.name = name;
    }
}

package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Game {

    static public String name;

    static {
        try {
            name = checkHeroName();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Hero hero;

    public Game() {
        hero = new Hero(name);
    }

    static public String checkHeroName() throws IOException {
        File data = new File("./src/Game/heroData.txt");
        BufferedReader br = new BufferedReader(new FileReader(data));

        String all;
        all = br.readLine();
        if (all == null) all = "";
        return all;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}

package Game;

public class Game {

    public String name = "";
    public Hero hero;

    public Game() {
        hero = new Hero(name);
    }

    public void begin(String n) {
        if (name.isEmpty()) name = n;
        hero.setName(name);
        System.out.println(hero.getName());
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

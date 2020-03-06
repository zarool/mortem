package Game;

public class Game {

    private String name;
    private Hero hero;

    public void begin(String n) {
        if(name.isEmpty()) name = n;
        hero.setName(name);
        System.out.println(hero.getName());
    }

}

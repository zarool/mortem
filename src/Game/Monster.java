package Game;

public class Monster {

    private String name;
    private int health;
    private int stamina;
    private int attack;
    private float strength;

    Monster(String name, float strength) {
        this.name = name;
        this.health = 10;
        this.stamina = 8;
        this.attack = 5;
        this.strength = strength;
    }
}

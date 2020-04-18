package Game;

public class Monster {

    private int health;
    private int stamina;
    private int attack;
    private float strength;

    Monster(float strength) {
        this.health = 10;
        this.stamina = 8;
        this.attack = 5;
        this.strength = strength;
    }
}

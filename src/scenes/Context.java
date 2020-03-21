package scenes;

import Game.Game;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private Game game = new Game();

    public Game getGame() {
        return game;
    }
}

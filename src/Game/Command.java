package Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Command {

    Hero hero;
    MediaPlayer bgMusic;
    int soundIndex = (int) (Math.random() * 2);

    Command(Hero hero) {
        this.hero = hero;

        Media sound = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/')
                + "/src/assets/music/bgsound" + soundIndex + ".mp3");
        bgMusic = new MediaPlayer(sound);
        bgMusic.setVolume(0);
        play(bgMusic);
    }

    private void play(MediaPlayer music) {
        music.play();

        music.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                changeMusic();
            }
        });
    }

    private void changeMusic() {
        bgMusic.stop();

        soundIndex = 1 - soundIndex;
        Media sound2 = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/')
                + "/src/assets/music/bgsound" + soundIndex + ".mp3");
        bgMusic = new MediaPlayer(sound2);
        play(bgMusic);
    }

    public String error(String msg) {
        return "Źle wpisania komenda, popraw ją, wojowniku";
    }

    public String gora(String msg) {
        if (hero.hasDir("gora")) {
            hero.setY(hero.getY() - 1);
            return msg;
        } else return error("");
    }

    public String prawo(String msg) {
        if (hero.hasDir("prawo")) {
            hero.setX(hero.getX() + 1);
            return msg;
        } else return error("");
    }

    public String dol(String msg) {
        if (hero.hasDir("dol")) {
            hero.setY(hero.getY() + 1);
            return msg;
        } else return error("");
    }

    public String lewo(String msg) {
        if (hero.hasDir("lewo")) {
            hero.setX(hero.getX() - 1);
            return msg;
        } else return error("");
    }

    public String pomoc(String msg) {
        return msg;
    }

    public String walka(String msg) {
        return msg;
    }

    public String seppuku(String msg) {
        hero.gameOver();
        return msg;
    }

    public String muzyka(String msg) {
        if (!bgMusic.getStatus().equals(MediaPlayer.Status.PLAYING)) bgMusic.play();

        return msg;
    }

    public String wycisz(String msg) {
        bgMusic.stop();
        return msg;
    }

    public String nastepna(String msg) {
        changeMusic();
        return msg;
    }
}

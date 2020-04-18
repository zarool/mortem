package Game;

public class Room {

    public void generateRoom() {
        float choice = (float) Math.random();
        /////50% monster room, 10% chest room, 40% free room
        if (choice < 0.5) {
            monsterRoom();
        } else if (choice < 0.9) {
            emptyRoom();
        } else {
            chestRoom();
        }
    }

    private void monsterRoom() {
    }

    private void emptyRoom() {

    }

    private void chestRoom() {
        
    }
}

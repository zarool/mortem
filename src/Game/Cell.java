package Game;

import java.util.ArrayList;
import java.util.Random;

public class Cell {

    /////1 wall, 0 open top-right-bottom-left

    int[] walls = {1, 1, 1, 1};

    // if visited .    if not #
    public boolean isVisited;
    public boolean isVisible = false;
    int x;
    int y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isVisited = false;
    }

    public Cell checkNeighbors(ArrayList<Cell> others, int cols, int rows) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        if (this.y > 2) {
            Cell top = others.get(Maze.getIndex(x, y - 2));
            if (top != null && !top.isVisited) neighbors.add(top);
        }

        if (this.x < cols - 3) {
            Cell right = others.get(Maze.getIndex(x + 2, y));
            if (right != null && !right.isVisited) neighbors.add(right);
        }

        if (this.y < rows - 3) {
            Cell bottom = others.get(Maze.getIndex(x, y + 2));
            if (bottom != null && !bottom.isVisited) neighbors.add(bottom);
        }

        if (this.x > 2) {
            Cell left = others.get(Maze.getIndex(x - 2, y));
            if (left != null && !left.isVisited) neighbors.add(left);
        }

        if (neighbors.size() > 0) {
            Random r = new Random();
            int n = Math.abs(r.nextInt()) % neighbors.size();
            return neighbors.get(n);
        } else return null;
    }


    ///setters and getters
    public void setVisited(boolean b) {
        this.isVisited = b;
    }

    public void setVisible(boolean b) {
        this.isVisible = b;
    }

    public boolean getVisited() {
        return this.isVisited;
    }

    public int getPathAmount() {
        int pathAmount = 0;
        for (int i = 0; i < walls.length; i++) {
            if (walls[i] == 0) pathAmount++;
        }
        return pathAmount;
    }

    public String toString() {
        String state = isVisited ? "." : "#";
        return "x: " + this.x + " | y: " + this.y + " | state: " + state + " |";
    }

    public ArrayList<String> getDir() {
        ArrayList<String> a = new ArrayList<String>();

        if (walls[0] == 0) a.add("gora");
        if (walls[1] == 0) a.add("prawo");
        if (walls[2] == 0) a.add("dol");
        if (walls[3] == 0) a.add("lewo");
        return a;
    }
}

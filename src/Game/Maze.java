package Game;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Stack;

public class Maze {

    //min val 3
    final static int NUMBER = 10;
    private static int cols;
    private static int rows;

    Cell current;
    ArrayList<Cell> cells;
    Stack<Cell> stack;
    ArrayList<String> map;

    Maze() {
        this.cells = new ArrayList<Cell>();
        this.stack = new Stack<Cell>();
        this.map = new ArrayList<String>();

        this.cols = NUMBER + 2;
        this.rows = NUMBER + 2;

        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.cols; x++) {

                this.cells.add(new Cell(x, y));
            }
        }

        this.current = cells.get(getIndex(1, 1));
        this.current.setVisited(true);
        this.current.setVisible(true);
    }

    void run() {
        Cell next = current.checkNeighbors(cells, cols, rows);

        if (next != null) {
            next.setVisited(true);

            stack.add(current);
            removeWalls(current, next);

            current = next;
        } else if (stack.size() > 0) {
            current = stack.remove(stack.size() - 1);
        }

        if (stack.size() > 0) {
            run();
        }
    }

    private void removeWalls(Cell current, Cell next) {
        int x = current.x - next.x;
        //left
        if (x > 0) {
            cells.get(getIndex(current.x - 1, current.y)).setVisited(true);
            cells.get(getIndex(current.x - 1, current.y)).walls[3] = 0;
            cells.get(getIndex(current.x - 1, current.y)).walls[1] = 0;

            current.walls[3] = 0;
            next.walls[1] = 0;
        }
        //right
        else if (x < 0) {
            cells.get(getIndex(current.x + 1, current.y)).setVisited(true);
            cells.get(getIndex(current.x + 1, current.y)).walls[3] = 0;
            cells.get(getIndex(current.x + 1, current.y)).walls[1] = 0;

            current.walls[1] = 0;
            next.walls[3] = 0;
        }

        int y = current.y - next.y;
        //top
        if (y > 0) {
            cells.get(getIndex(current.x, current.y - 1)).setVisited(true);
            cells.get(getIndex(current.x, current.y - 1)).walls[0] = 0;
            cells.get(getIndex(current.x, current.y - 1)).walls[2] = 0;

            current.walls[0] = 0;
            next.walls[2] = 0;
        }
        //bottom
        else if (y < 0) {
            cells.get(getIndex(current.x, current.y + 1)).setVisited(true);
            cells.get(getIndex(current.x, current.y + 1)).walls[0] = 0;
            cells.get(getIndex(current.x, current.y + 1)).walls[2] = 0;

            current.walls[2] = 0;
            next.walls[0] = 0;
        }
    }

    public void updateMap(int pX, int pY) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                cells.get(getIndex(pX + i, pY + j)).isVisible = true;
            }
        }

        this.map.clear();
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.cols; x++) {
                if (cells.get(getIndex(x, y)).isVisible) {
                    String val = cells.get(getIndex(x, y)).isVisited ? "•" : "#";
                    this.map.add(val);
                } else {
                    this.map.add("#");
                }
            }
        }
        this.map.set(getIndex(pX, pY), "X");

    }

    public void fillGrid(GridPane grid, int pX, int pY) {

        for (int y = 0; y <= 5; y++) {
            for (int x = 0; x <= 5; x++) {
                String val = this.map.get(getIndex(x, y));
                grid.add(new Label(val), x, y);
            }
        }
    }

    ////////////////////////////////
    public ArrayList<String> getMap(int pX, int pY) {
        updateMap(pX, pY);
        return this.map;
    }

    public String getGridString() {
        StringBuilder string = new StringBuilder();

        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.cols; x++) {
                String val = cells.get(getIndex(x, y)).isVisited ? "•" : "#";
                string.append(val + " ");
            }
            string.append("\n");
        }

        return string.toString();
    }

    public static int getIndex(int x, int y) {
        if (x < 0 || y < 0 || x > cols - 1 || y > rows - 1) return 0;
        return (y * cols) + x;
    }

    public ArrayList getCells() {
        return this.cells;
    }

    public int getCols() {
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getPath(int x, int y) {
        return this.cells.get(getIndex(x, y)).getPathAmount();
    }

    public ArrayList<String> getDir(int x, int y) {
        return this.cells.get(getIndex(x, y)).getDir();
    }
}

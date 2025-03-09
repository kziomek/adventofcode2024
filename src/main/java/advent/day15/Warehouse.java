package advent.day15;

import java.util.List;

public class Warehouse {

    char[][] grid;
    char[] robotMoves;

    public Warehouse(char[][] grid, char[] robotMoves) {
        this.grid = grid;
        this.robotMoves = robotMoves;
    }

    public void print() {
        System.out.println("=====GRID=====");
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }

        System.out.println("=====MOVES=====");
        System.out.println(robotMoves);
    }
}

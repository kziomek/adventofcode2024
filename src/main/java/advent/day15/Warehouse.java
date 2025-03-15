package advent.day15;

public class Warehouse {

    public static final int[] DIRECTION_RIGHT = {0, 1};
    public static final int[] DIRECTION_LEFT = {0, -1};
    public static final int[] DIRECTION_UP = {-1, 0};
    public static final int[] DIRECTION_DOWN = {1, 0};
    char[][] grid;
    char[] robotMoves;
    int[] robotPosition;

    public Warehouse(char[][] grid, char[] robotMoves) {
        this.grid = grid;
        this.robotMoves = robotMoves;
        this.robotPosition = findRobotPosition();
    }

    public void print() {
        printGrid();

        System.out.println("=====MOVES=====");
        System.out.println(robotMoves);
    }

    public void printGrid() {
        System.out.println("=====GRID=====");
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    public int[] findRobotPosition() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '@') {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalStateException("Robot position not found");
    }

    public void applyMoves() {
        for (char robotMove : robotMoves) {
            applyMove(robotMove);
        }
    }

    private void applyMove(char robotMove) {
        System.out.println("Move " + robotMove);
        switch (robotMove) {
            case '^' -> move(DIRECTION_UP);
            case 'v' -> move(DIRECTION_DOWN);
            case '<' -> move(DIRECTION_LEFT);
            case '>' -> move(DIRECTION_RIGHT);
        }
//        printGrid();
    }

    private void move(int[] direction) {
        int[] emptyPos = findEmptyPos(direction);
        if (emptyPos == null) {
            System.out.println(" can't move ");
            return;
        }
        System.out.println(" can move ");

        int[] robotAdjacentPos = new int[] {robotPosition[0] + direction[0], robotPosition[1] + direction[1]};
        if (!isEmptyPos(robotAdjacentPos)) {
            moveBox(robotAdjacentPos, emptyPos);
        }
        moveRobot(robotAdjacentPos);
    }

    private void moveBox(int[] boxPosition, int[] emptyPos) {
        if (grid[boxPosition[0]][boxPosition[1]] != 'O') {
            throw new IllegalStateException("not found box");
        }
        grid[emptyPos[0]][emptyPos[1]] = 'O';
        grid[boxPosition[0]][boxPosition[1]] = '.';
    }

    private void moveRobot(int[] pos) {
        grid[pos[0]][pos[1]] = '@';
        grid[robotPosition[0]][robotPosition[1]] = '.';
        this.robotPosition = pos;
    }

    private boolean isEmptyPos(int[] pos) {
        return grid[pos[0]][pos[1]] == '.';
    }

    //    private boolean canMove(int[] direction) {
    //        int[] pos = new int[] {robotPosition[0] + direction[0], robotPosition[1] + direction[1]};
    //        while (isInGrid(pos)) {
    //            if (grid[pos[0]][pos[1]] == '#') {
    //                break;
    //            }
    //            if (grid[pos[0]][pos[1]] == '.') {
    //                return true;
    //            }
    //            pos[0] = pos[0] + direction[0];
    //            pos[1] = pos[1] + direction[1];
    //        }
    //        return false;
    //    }

    private int[] findEmptyPos(int[] direction) {
        int[] pos = new int[] {robotPosition[0] + direction[0], robotPosition[1] + direction[1]};
        while (isInGrid(pos)) {
            if (grid[pos[0]][pos[1]] == '#') {
                break;
            }
            if (grid[pos[0]][pos[1]] == '.') {
                return pos;
            }
            pos[0] = pos[0] + direction[0];
            pos[1] = pos[1] + direction[1];
        }
        return null;
    }

    private boolean isInGrid(int[] pos) {
        return pos[0] >= 0 & pos[1] >= 0 && pos[0] < grid.length && pos[1] < grid[0].length;
    }

    public int coordinatesValue() {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'O') {
                    count += 100 * i + j;
                }
            }
        }
        return count;
    }
}

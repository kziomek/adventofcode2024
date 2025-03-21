package advent.day15;

public class Warehouse2 {

    public static final int[] DIRECTION_RIGHT = {0, 1};
    public static final int[] DIRECTION_LEFT = {0, -1};
    public static final int[] DIRECTION_UP = {-1, 0};
    public static final int[] DIRECTION_DOWN = {1, 0};
    char[][] grid;
    char[] robotMoves;
    int[] robotPosition;

    public Warehouse2(char[][] grid, char[] robotMoves) {
        this.grid = grid;
        this.robotMoves = robotMoves;
        this.robotPosition = findRobotPosition();
    }

    public Warehouse2(Warehouse warehouse) {
        char[][] gridPart1 = warehouse.grid;
        char[][] grid = new char[gridPart1.length][2 * gridPart1[0].length];
        for (int i = 0; i < gridPart1.length; i++) {
            for (int j = 0; j < gridPart1[0].length; j++) {
                switch (gridPart1[i][j]) {
                    case '#' -> {
                        grid[i][2 * j] = '#';
                        grid[i][2 * j + 1] = '#';
                    }
                    case 'O' -> {
                        grid[i][2 * j] = '[';
                        grid[i][2 * j + 1] = ']';
                    }
                    case '.' -> {
                        grid[i][2 * j] = '.';
                        grid[i][2 * j + 1] = '.';
                    }
                    case '@' -> {
                        grid[i][2 * j] = '@';
                        grid[i][2 * j + 1] = '.';
                    }
                }
            }
        }
        this.grid = grid;
        this.robotMoves = warehouse.robotMoves;
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
//            printGrid();
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
        int[] robotAdjacentPos = new int[] {robotPosition[0] + direction[0], robotPosition[1] + direction[1]};
        if (isEmptyPos(robotAdjacentPos)) {
            moveRobot(robotAdjacentPos);
        } else {
            moveBoxesAndRobot(robotAdjacentPos, direction);
        }
    }

    private void moveBoxesAndRobot(int[] robotAdjacentPos, int[] direction) {
        if (direction[0] == 0) { //left right
            int i = robotAdjacentPos[0];
            int[] emptyPos = findEmptyPos(direction);

            if (emptyPos != null) {
                System.out.println("can move");
                while (emptyPos[1] != robotAdjacentPos[1]) {
                    grid[i][emptyPos[1]] = grid[i][emptyPos[1] - direction[1]];
                    emptyPos[1] = emptyPos[1] - direction[1];
                }
                moveRobot(robotAdjacentPos);
            } else {
                System.out.println("can't move");
            }
        }

        if (direction[1] == 0) { //vertical
            if (canPushBoxes(robotAdjacentPos, direction)) {
                pushBoxes(robotAdjacentPos, direction);
                moveRobot(robotAdjacentPos);
            }
        }
    }

    private boolean canPushBoxes(int[] pos, int[] direction) {
        if (grid[pos[0]][pos[1]] == '.') {
            return true;
        }
        if (grid[pos[0]][pos[1]] == '#') {
            return false;
        }
        int[] leftPart = new int[2];
        int[] rightPart = new int[2];
        leftPart[0] = pos[0];
        rightPart[0] = pos[0];

        if (grid[pos[0]][pos[1]] == '[') {
            leftPart[1] = pos[1];
            rightPart[1] = pos[1] + 1;
        } else if (grid[pos[0]][pos[1]] == ']') {
            leftPart[1] = pos[1] - 1;
            rightPart[1] = pos[1];
        }

        return canPushBoxes(new int[] {pos[0] + direction[0], leftPart[1]}, direction)
            && canPushBoxes(new int[] {pos[0] + direction[0], rightPart[1]}, direction);
    }

    private void pushBoxes(int[] pos, int[] direction) {
        if (grid[pos[0]][pos[1]] == '.') {
            return;
        }
        if (grid[pos[0]][pos[1]] == '#') {
            throw new IllegalStateException("unexpected #");
        }
        int[] leftPart = new int[2];
        int[] rightPart = new int[2];
        leftPart[0] = pos[0];
        rightPart[0] = pos[0];

        if (grid[pos[0]][pos[1]] == '[') {
            leftPart[1] = pos[1];
            rightPart[1] = pos[1] + 1;
        } else if (grid[pos[0]][pos[1]] == ']') {
            leftPart[1] = pos[1] - 1;
            rightPart[1] = pos[1];
        }

        pushBoxes(new int[] {pos[0] + direction[0], leftPart[1]}, direction);
        pushBoxes(new int[] {pos[0] + direction[0], rightPart[1]}, direction);

        grid[pos[0] + direction[0]][leftPart[1]] = '[';
        grid[pos[0] + direction[0]][rightPart[1]] = ']';
        grid[pos[0]][leftPart[1]] = '.';
        grid[pos[0]][rightPart[1]] = '.';
    }


    private void moveRobot(int[] pos) {
        grid[pos[0]][pos[1]] = '@';
        grid[robotPosition[0]][robotPosition[1]] = '.';
        this.robotPosition = pos;
    }

    private boolean isEmptyPos(int[] pos) {
        return grid[pos[0]][pos[1]] == '.';
    }

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
                if (grid[i][j] == '[') {
//                    System.out.println(100 * i + j);
                    count += 100 * i + j;
                }
            }
        }
        return count;
    }
}

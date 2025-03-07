package advent.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RestroomRedoubt {

    public static void main(String[] args) throws IOException {
        List<Robot> robots = Files.readAllLines(Path.of("src/main/resources/day14/example.txt"))
            .stream()
            .map(Robot::parse)
            .toList();

        int xBoundry = 11;
        int yBoundry = 7;
        int moves = 100;

        int[][] posCount = posCount(yBoundry, xBoundry);
        for (Robot robot : robots) {
            robot.move(moves, xBoundry, yBoundry);
            posCount[robot.sY][robot.sX]++;
            System.out.println(robot);
        }

        int result = safetyFactor(posCount, xBoundry, yBoundry);

        System.out.println(result);
    }

    private static int safetyFactor(int[][] posCount, int xBoundry, int yBoundry){
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (int i = 0; i < posCount.length; i++) {
            for (int j = 0; j < posCount[0].length; j++) {
                int iHalf = (yBoundry - 1) / 2;
                int jHalf = (xBoundry - 1) / 2;
                if (i == iHalf || j == jHalf) {
                    continue;
                }
                if (i < iHalf && j < jHalf) {
                    q1 += posCount[i][j];
                } else if (i < iHalf) {
                    q2 += posCount[i][j];
                } else if (j < jHalf) {
                    q3 += posCount[i][j];
                } else {
                    q4 += posCount[i][j];
                }
            }
        }
        return q1 * q2 * q3 * q4;
    }

    private static int[][] posCount(int yBoundry, int xBoundry) {
        int[][] tab = new int[yBoundry][xBoundry];
        for (int i = 0; i < yBoundry; i++) {
            for (int j = 0; j < xBoundry; j++) {
                tab[i][j] = 0;
            }
        }
        return tab;
    }
}

package advent.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RestroomRedoubt {

    public static void main(String[] args) throws IOException {
        //        List<Robot> robots = Files.readAllLines(Path.of("src/main/resources/day14/example.txt"))
        List<Robot> robots = Files.readAllLines(Path.of("src/main/resources/day14/my-input.txt"))
            .stream()
            .map(Robot::parse)
            .toList();

        int xBoundry = 101;
        int yBoundry = 103;
        int moves = 100000;

        long minSafetyFactor = Long.MAX_VALUE;
        //        int result = 0;
        for (int i = 0; i < moves; i++) {
            for (Robot robot : robots) {
                robot.move(1, xBoundry, yBoundry);
            }
            int[][] posCount = posCount(yBoundry, xBoundry);
            for (Robot robot : robots) {
                posCount[robot.sY][robot.sX]++;
                //                System.out.println(robot);
            }

            long safetyFactor = safetyFactorPart2(posCount, xBoundry, yBoundry);

            if (safetyFactor < minSafetyFactor) {
                minSafetyFactor = safetyFactor;
                System.out.println("i=" + i + "seconds lapsed " + (i + 1));
                System.out.println("Min safety factor " + minSafetyFactor);
                printPosCount(posCount);
            }
        }
    }

    //        int[][] posCount = posCount(yBoundry, xBoundry);
    //        for (Robot robot : robots) {
    //            posCount[robot.sY][robot.sX]++;
    //            System.out.println(robot);
    //        }

    //        System.out.println(result);
    //}

    private static void printPosCount(int[][] posCount) {
        System.out.println("=========");
        for (int[] ints : posCount) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    System.out.print('.');
                } else {
                    System.out.print(anInt);
                }
            }
            System.out.println();
        }
    }

    private static long safetyFactorPart2(int[][] posCount, int xBoundry, int yBoundry) {
        long q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (int i = 0; i < posCount.length; i++) {
            for (int j = 0; j < posCount[0].length; j++) {
                int iHalf = (yBoundry - 1) / 2;
                int jHalf = (xBoundry - 1) / 2;
                if (i == iHalf || j == jHalf) {
                    continue;
                }
                if (posCount[i][j] == 0) {
                    continue;
                }
                if (i < iHalf && j < jHalf) {
                    q1++;
                } else if (i < iHalf) {
                    q2++;
                } else if (j < jHalf) {
                    q3++;
                } else {
                    q4++;
                }
            }
        }
        return q1 * q2 * q3 * q4;
    }

    private static long safetyFactor(int[][] posCount, int xBoundry, int yBoundry) {
        long q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (int i = 0; i < posCount.length; i++) {
            for (int j = 0; j < posCount[0].length; j++) {
                int iHalf = (yBoundry - 1) / 2;
                int jHalf = (xBoundry - 1) / 2;
                if (i == iHalf || j == jHalf) {
                    continue;
                }
                if (posCount[i][j] == 0) {
                    continue;
                }
                if (i < iHalf && j < jHalf) {
                    q1++;
                } else if (i < iHalf) {
                    q2++;
                } else if (j < jHalf) {
                    q3++;
                } else {
                    q4++;
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

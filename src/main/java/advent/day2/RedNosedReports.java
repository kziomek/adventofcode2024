package advent.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * In Part2 we use index j to skip bad level.
 * To cover edge case bad level is first or last element (initial pos of i) we process table from two directions.
 * <p>
 * Other way of solving it would be preprocessing array to remove one level from report before calling isReportSafe, but it would be slower as well.
 * <p>
 * There is room for deduplicating code.
 */
public class RedNosedReports {

    public static void main(String[] args) throws IOException {
        //        Integer[][] reports = Files.readAllLines(Path.of("src/main/resources/day2/example.txt"))
        Integer[][] reports = Files.readAllLines(Path.of("src/main/resources/day2/my-input.txt"))
            .stream()
            .map(line -> Arrays.stream(line.split(" ")).map(Integer::valueOf).toArray(Integer[]::new))
            .toArray(Integer[][]::new);

        int count = 0;

        for (int i = 0; i < reports.length; i++) {
            if (isReportSafe(reports[i])) {
                System.out.println("Line " + i + " is safe;");
                count++;
            } else {
                System.out.println("Line " + i + " is not safe;");
            }
        }

        System.out.println("result: " + count);
    }

    private static boolean isReportSafe(Integer[] report) {
        return testForward(report, difference -> difference < 1 || difference > 3)
            || testForward(report, difference -> difference < -3 || difference > -1)
            || testBackward(report, difference -> difference < 1 || difference > 3)
            || testBackward(report, difference -> difference < -3 || difference > -1);
    }

    private static boolean testBackward(Integer[] report, Predicate<Integer> predicate) {
        int badLevelCountReverse = 0;
        int i;
        int j;
        i = report.length - 1;
        j = i - 1;
        while (j >= 0) {
            int difference = report[i] - report[j];
            if (predicate.test(difference)) {
                badLevelCountReverse++;
                j--;
                continue;
            }
            i = j;
            j--;
        }
        return badLevelCountReverse <= 1;
    }

    private static boolean testForward(Integer[] report, Predicate<Integer> predicate) {
        int badLevelCount = 0;
        int i = 0, j = 1;
        while (j <= report.length - 1) {
            int difference = report[i] - report[j];
            if (predicate.test(difference)) {
                badLevelCount++;
                j++;
                continue;
            }
            i = j;
            j++;
        }
        return badLevelCount <= 1;
    }

    public static void print(Integer[] report) {
        for (Integer i : report) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

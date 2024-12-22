package advent.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

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
        int badLevelCount = 0;
        int badLevelCountReverse = 0;

        int i = 0, j = 1;

        while (j <= report.length - 1) {
            int difference = report[i] - report[j];
            if (difference < 1 || difference > 3) {
                badLevelCount++;
            }
            i = j;
            j++;
        }

        i = report.length - 1;
        j = i - 1;
        while (j >= 0) {
            int difference = report[i] - report[j];
            if (difference < 1 || difference > 3) {
                badLevelCountReverse++;
            }
            i = j;
            j--;
        }

        return badLevelCount == 0 || badLevelCountReverse == 0;
    }

    public static void print(Integer[] report) {
        for (Integer i : report) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

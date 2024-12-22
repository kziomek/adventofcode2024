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
        boolean flag1 = true;
        boolean flag2 = true;
        for (int i = 0; i < report.length - 1; i++) {
            int difference = report[i] - report[i + 1];
            if (difference < 1 || difference > 3) {
                flag1 = false;
            }
        }

        for (int i = report.length - 1; i > 0; i--) {
            int difference = report[i] - report[i - 1];
            if (difference < 1 || difference > 3) {
                flag2 = false;
            }
        }
        if (flag1 || flag2) {
            print(report);
        }

        return flag1 || flag2;
    }

    public static void print(Integer[] report) {
        for (Integer i : report) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

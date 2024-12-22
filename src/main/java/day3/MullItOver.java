package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MullItOver {

    // mul(123,4)
    //mul(xxx,xxx)
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/day3/my-input.txt"));
        //        System.out.println(lines.size());

        //        List<String> lines =List.of("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))");
        //        List<String> lines = List.of("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))");
        String line = String.join("", lines);

        int total = scan(line);
        System.out.println("Result " + total);
    }

    private static int scan(String line) {
        int total = 0;
        int i = 0;
        Boolean enabled = Boolean.TRUE;
        while (i < line.length()) {
            if (line.charAt(i) == 'm') {
                int result = parseMulAt(line, i);
                if (enabled.equals(Boolean.TRUE)) {
                    total += result;
                }
            }
            if (line.charAt(i) == 'd') {
                Boolean enabledResult = parseD(line, i);
                if (enabledResult != null) {
                    enabled = enabledResult;
                }
            }
            i++;
        }
        return total;
    }

    private static Boolean parseD(String line, int i) {
        if (line.charAt(i) == 'd'
            && line.charAt(i + 1) == 'o'
            && line.charAt(i + 2) == 'n'
            && line.charAt(i + 3) == '\''
            && line.charAt(i + 4) == 't'
            && line.charAt(i + 5) == '('
            && line.charAt(i + 6) == ')') {
            System.out.println("don't");
            return Boolean.FALSE;
        }
        if (line.charAt(i) == 'd'
            && line.charAt(i + 1) == 'o'
            && line.charAt(i + 2) == '('
            && line.charAt(i + 3) == ')') {
            System.out.println("do");
            return Boolean.TRUE;
        }
        return null;
    }

    private static int parseMulAt(String line, int i) {
        int a, b;
        int aLength, bLength;

        if (line.charAt(i) != 'm'
            || line.charAt(i + 1) != 'u'
            || line.charAt(i + 2) != 'l'
            || line.charAt(i + 3) != '('

        ) {
            return 0;
        }

        if (!Character.isDigit(line.charAt(i + 4))) {
            return 0;
        }
        a = readNumber(line, i + 4);
        aLength = Integer.toString(a).length();

        if (line.charAt(i + aLength + 4) != ',') {
            return 0;
        }

        if (!Character.isDigit(line.charAt(i + aLength + 5))) {
            return 0;
        }
        b = readNumber(line, i + aLength + 5);
        bLength = Integer.toString(b).length();

        if (line.charAt((i + aLength + bLength + 5)) != ')') {
            return 0;
        }
        System.out.println("mul " + a + " " + b + " = " + a * b);
        return a * b;
    }

    private static int readNumber(String line, int i) {
        int num = 0;
        char c1 = line.charAt(i);
        if (Character.isDigit(c1)) {
            num = Character.getNumericValue(c1);
        }

        char c2 = line.charAt(i + 1);
        if (!Character.isDigit(c2)) {
            return num;
        } else {
            num = 10 * num + Character.getNumericValue(c2);
        }

        char c3 = line.charAt(i + 2);
        if (!Character.isDigit(c3)) {
            return num;
        } else {
            num = 10 * num + Character.getNumericValue(c3);
        }
        return num;
    }
}

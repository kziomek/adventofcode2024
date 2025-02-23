package advent.day13;

import javax.crypto.Mac;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Machine> parse(String input) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(input));
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < (lines.size() + 1) / 4; i++) {
            machines.add(parse(lines, i));
        }
        return machines;
    }

    private static Machine parse(List<String> lines, int i) {
        String buttonA = lines.get(4 * i);
        String buttonB = lines.get(4 * i + 1);
        String prize = lines.get(4 * i + 2);

        String[] buttonAV = buttonA.split(":")[1].strip().split(", ");
        int axv = Integer.parseInt(buttonAV[0].split("\\+")[1]);
        int ayv = Integer.parseInt(buttonAV[1].split("\\+")[1]);

        String[] buttonBV = buttonB.split(":")[1].strip().split(", ");
        int bxv = Integer.parseInt(buttonBV[0].split("\\+")[1]);
        int byv = Integer.parseInt(buttonBV[1].split("\\+")[1]);

        String[] prizeP = prize.split(":")[1].strip().split(", ");
        int xp = Integer.parseInt(prizeP[0].split("=")[1]);
        int yp = Integer.parseInt(prizeP[1].split("=")[1]);

        return new Machine(axv, bxv, xp, ayv, byv, yp);
    }
}

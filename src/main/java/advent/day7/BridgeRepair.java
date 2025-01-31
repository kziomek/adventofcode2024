package advent.day7;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BridgeRepair {

    public static void main(String[] args) throws IOException {
//        List<Calibration> list = Files.readAllLines(Path.of("src/main/resources/day7/example.txt"))
        List<Calibration> list = Files.readAllLines(Path.of("src/main/resources/day7/my-input.txt"))
            .stream()
            .map(line -> {
                String[] split = line.split(":");
                List<BigInteger> nums = Arrays.stream(split[1].trim().split(" ")).map(BigInteger::new).toList();
                return new Calibration(new BigInteger( split[0]), nums);
            }).toList();

        BigInteger result = runPart1(list);
        System.out.println("Part 1 result: " + result.toString());
    }

    private static BigInteger runPart1(List<Calibration> list) {
        BigInteger count = BigInteger.ZERO;
        for (Calibration calibration : list) {
            if (isValidCalibration(calibration, calibration.numbers.get(0), 1)) {
                count = count.add(calibration.testValue);
            }
        }

        return count;
    }

    private static boolean isValidCalibration(Calibration calibration, BigInteger currValue, int i) {

        return isValidCalibration(calibration, currValue, i, '*')
            || isValidCalibration(calibration, currValue, i, '+')
            || isValidCalibration(calibration, currValue, i, '|');
    }

    private static boolean isValidCalibration(Calibration calibration, BigInteger currValue, int i, char op) {
        if (calibration.numbers.size() == i) {
            return currValue.equals(calibration.testValue);
        }
        if (op == '*') {
            currValue = currValue.multiply(calibration.numbers.get(i));
        }
        if (op == '+') {
            currValue = currValue.add(calibration.numbers.get(i));
        }
        if (op == '|') {
            BigInteger multiply = new BigInteger("10").pow(calibration.numbers.get(i).toString().length());
            currValue = currValue.multiply(multiply).add(calibration.numbers.get(i));
        }
        return isValidCalibration(calibration, currValue, i + 1, '*')
            || isValidCalibration(calibration, currValue, i + 1, '+')
            || isValidCalibration(calibration, currValue, i + 1, '|');
    }
}

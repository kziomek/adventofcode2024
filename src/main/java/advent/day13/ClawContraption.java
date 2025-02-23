package advent.day13;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class ClawContraption {

    public static void main(String[] args) throws IOException {
        //        List<Machine> machines = Parser.parse("src/main/resources/day13/example.txt");
        List<Machine> machines = Parser.parse("src/main/resources/day13/my-input.txt");

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(machines);

        for (Machine machine : machines) {
            System.out.println(machine.a());
            System.out.println(machine.b());
            System.out.println(machine.cost());
            System.out.println();
        }

        BigInteger cost = countCost(machines);
        System.out.println("Result part 2 = " + cost.toString());
    }

    private static BigInteger countCost(List<Machine> machines) {
        return machines
            .stream()
            .map(Machine::cost)
            .reduce(BigInteger.ZERO, BigInteger::add);
    }
}

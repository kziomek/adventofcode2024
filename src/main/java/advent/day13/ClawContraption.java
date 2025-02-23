package advent.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ClawContraption {

    public static void main(String[] args) throws IOException {
//        List<Machine> machines = Parser.parse("src/main/resources/day13/example.txt");
        List<Machine> machines = Parser.parse("src/main/resources/day13/my-input.txt");

        System.out.println(machines);

        for (Machine machine : machines) {
            System.out.println(machine.a());
            System.out.println(machine.b());
            System.out.println();
        }

        int cost = countCost(machines);
        System.out.println("Result part 1 = " + cost);
    }

    private static int countCost(List<Machine> machines) {
        return machines
            .stream()
            .map(Machine::cost)
            .reduce(0, Integer::sum);
    }
}

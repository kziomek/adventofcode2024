package advent.day15;

import java.io.IOException;

public class WarehouseWoes {

    public static void main(String[] args) throws IOException {
//        runPart1();
        runPart2();
    }

    private static void runPart1() throws IOException {
        //        Warehouse warehouse = Parser.parse("src/main/resources/day15/small-example.txt");
        Warehouse warehouse = Parser.parse("src/main/resources/day15/example.txt");
        //                Warehouse warehouse = Parser.parse("src/main/resources/day15/my-input.txt");
        warehouse.print();

        warehouse.applyMoves();
        int result = warehouse.coordinatesValue();
        System.out.println("Result part 1 " + result);
    }

    private static void runPart2() throws IOException {
//                Warehouse2 warehouse = new Warehouse2(Parser.parse("src/main/resources/day15/part2-example.txt"));
//        Warehouse2 warehouse = new Warehouse2(Parser.parse("src/main/resources/day15/example.txt"));
                        Warehouse2 warehouse = new Warehouse2(Parser.parse("src/main/resources/day15/my-input.txt"));
        warehouse.print();

        warehouse.applyMoves();
        int result = warehouse.coordinatesValue();
        System.out.println("Result part 2 " + result);
    }
}

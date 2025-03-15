package advent.day15;

import java.io.IOException;

public class WarehouseWoes {

    public static void main(String[] args) throws IOException {
//        Warehouse warehouse = Parser.parse("src/main/resources/day15/small-example.txt");
//                Warehouse warehouse = Parser.parse("src/main/resources/day15/example.txt");
                Warehouse warehouse = Parser.parse("src/main/resources/day15/my-input.txt");
        warehouse.print();

//        int[] robotPos = warehouse.findRobotPosition();
//        System.out.println(robotPos[0] + " " + robotPos[1]);

        warehouse.applyMoves();
        int result = warehouse.coordinatesValue();
        System.out.println("Result part 1 " + result);
    }
}

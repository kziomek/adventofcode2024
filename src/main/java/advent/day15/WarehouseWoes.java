package advent.day15;

import java.io.IOException;

public class WarehouseWoes {

    public static void main(String[] args) throws IOException {
        Warehouse warehouse = Parser.parse("src/main/resources/day15/small-example.txt");
//        Warehouse warehouse = Parser.parse("src/main/resources/day15/example.txt");
        warehouse.print();
    }

}

package advent.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ResonantCollinearity {

    public static void main(String[] args) throws IOException {

//        char[][] inputGrid = Files.readAllLines(Path.of("src/main/resources/day8/example.txt"))
        char[][] inputGrid = Files.readAllLines(Path.of("src/main/resources/day8/my-input.txt"))
            .stream().map(String::toCharArray)
            .toArray(char[][]::new);

        Set<Antenna> antennaSet = collectAntennas(inputGrid);
        System.out.println(antennaSet);

        Map<Character, List<Antenna>> map = toMap(antennaSet);
        System.out.println(map);

        Set<Antinode> antinodes = calculateAntinodes(map);
        antinodes = filterByGridBoundries(antinodes, inputGrid.length, inputGrid[0].length);

        System.out.println("Result part 1: " + antinodes.size());
    }

    private static Set<Antinode> filterByGridBoundries(Set<Antinode> antinodes, int iSize, int jSize) {
        return antinodes
            .stream()
            .filter(a -> a.i >= 0 && a.i < iSize && a.j >= 0 && a.j < jSize)
            .collect(Collectors.toSet());
    }

    private static Set<Antinode> calculateAntinodes(Map<Character, List<Antenna>> map) {
        Set<Antinode> allAntinodes = new HashSet<>();
        for (Character frequency : map.keySet()) {
            List<Antenna> antennas = map.get(frequency);
            Set<Antinode> antinodes = calculateAntinodes(antennas);
            allAntinodes.addAll(antinodes);
        }

        return allAntinodes;
    }

    private static Set<Antinode> calculateAntinodes(List<Antenna> antennas) {
        Set<Antinode> antinodesByFrequency = new HashSet<>();
        for (int i = 0; i < antennas.size() - 1; i++) {
            for (int j = 1; j < antennas.size(); j++) {
                Antenna a = antennas.get(i);
                Antenna b = antennas.get(j);
                Set<Antinode> antinodes = calculateAntinodes(a, b);
                antinodesByFrequency.addAll(antinodes);
            }
        }
        return antinodesByFrequency;
    }

    private static Set<Antinode> calculateAntinodes(Antenna a, Antenna b) {
        System.out.println("Calculate antinodes for antennas: ");
        System.out.println(a);
        System.out.println(b);

        Set<Antinode> antinodes = new HashSet<>();
        int distanceJ = b.j - a.j;
        int distanceI = b.i - a.i;
        //        double thirdOfDistanceJ = distanceJ / 3;
        //        double thirdOfDistanceI = distanceI / 3;
        //
        //        // 2 internal points

        // 2 external points
        if (a.i + distanceI != b.i) {
            antinodes.add(new Antinode(a.i + distanceI, a.j + distanceJ));
        }
        if (a.i - distanceI != b.i) {
            antinodes.add(new Antinode(a.i - distanceI, a.j - distanceJ));
        }
        if (b.i + distanceI != a.i) {
            antinodes.add(new Antinode(b.i + distanceI, b.j + distanceJ));
        }
        if (b.i - distanceI != a.i) {
            antinodes.add(new Antinode(b.i - distanceI, b.j - distanceJ));
        }

        System.out.println(antinodes);

        return antinodes;
    }

    private static Map<Character, List<Antenna>> toMap(Set<Antenna> antennaSet) {
        Map<Character, List<Antenna>> map = new HashMap<>();
        for (Antenna antenna : antennaSet) {
            if (!map.containsKey(antenna.frequency)) {
                map.put(antenna.frequency, new ArrayList<>());
            }
            map.get(antenna.frequency).add(antenna);
        }
        return map;
    }

    private static Set<Antenna> collectAntennas(char[][] inputGrid) {
        Set<Antenna> antennaSet = new HashSet<>();
        for (int i = 0; i < inputGrid.length; i++) {
            for (int j = 0; j < inputGrid[0].length; j++) {
                if (inputGrid[i][j] != '.') {
                    antennaSet.add(new Antenna(i, j, inputGrid[i][j]));
                }
            }
        }
        return antennaSet;
    }
    //split coordinates by frequency
    //for each pair calculate all antinode locations - 4 possible (2 outside, 2 inside)
    //ifantinode within grid, add to set
}

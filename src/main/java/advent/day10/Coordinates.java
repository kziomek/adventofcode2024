package advent.day10;

import java.util.Objects;

public class Coordinates {
    int i;
    int j;

    public Coordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates that = (Coordinates) o;
        return i == that.i && j == that.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
            "i=" + i +
            ", j=" + j +
            '}';
    }
}

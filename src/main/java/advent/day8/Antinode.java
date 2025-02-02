package advent.day8;

import java.util.Objects;

public class Antinode {
    int i, j;

    public Antinode(int i, int j) {

        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Antinode antinode = (Antinode) o;
        return i == antinode.i && j == antinode.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "Antinode{" +
            "i=" + i +
            ", j=" + j +
            '}';
    }
}


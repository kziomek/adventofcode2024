package advent.day8;

public class Antenna {
    int i, j;
    char frequency;

    public Antenna(int i, int j, char frequency) {
        this.i = i;
        this.j = j;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Antenna{" +
            "i=" + i +
            ", j=" + j +
            ", frequency=" + frequency +
            '}';
    }
}

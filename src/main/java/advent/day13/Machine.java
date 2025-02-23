package advent.day13;

public class Machine {
    int axv;
    int bxv;
    int xp;

    int ayv;
    int byv;
    int yp;

    public Machine(int axv, int bxv, int xp, int ayv, int byv, int yp) {
        this.axv = axv;
        this.bxv = bxv;
        this.xp = xp;
        this.ayv = ayv;
        this.byv = byv;
        this.yp = yp;
    }

    public double a() {
        return (double) (bxv * yp - byv * xp) / (bxv * ayv - byv * axv);
    }

    public double b() {
        return (yp - ayv * a()) / byv;
    }

    @Override
    public String toString() {
        return "Machine{" +
            "axv=" + axv +
            ", bxv=" + bxv +
            ", xp=" + xp +
            ", ayv=" + ayv +
            ", byv=" + byv +
            ", yp=" + yp +
            '}';
    }

    public int cost() {
        if (!isValid()) {
            return 0;
        }
        return 3 * (int) a() + (int) b();
    }

    public boolean isValid() {
        return a() == (int) a() && b() == (int) b();
    }
}

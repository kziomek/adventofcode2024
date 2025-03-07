package advent.day14;

public class Robot {
    int sX;
    int sY;
    int vX;
    int vY;

    public Robot(int sX, int sY, int vX, int vY) {
        this.sX = sX;
        this.sY = sY;
        this.vX = vX;
        this.vY = vY;
    }

    public static Robot parse(String line) {
        String[] split = line.split(" ");
        String[] pos = split[0].split("=")[1].split(",");
        String[] vel = split[1].split("=")[1].split(",");

        return new Robot(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(vel[0]), Integer.parseInt(vel[1]));
    }

    @Override
    public String toString() {
        return "Robot{" +
            "sX=" + sX +
            ", sY=" + sY +
            ", vX=" + vX +
            ", vY=" + vY +
            '}';
    }

    public void move(int moves, int xBoundry, int yBoundry) {
        sX = (moves * xBoundry + sX + moves * vX) % xBoundry;
        sY = (moves * yBoundry + sY + moves * vY) % yBoundry;
    }

    public int getsX() {
        return sX;
    }

    public int getsY() {
        return sY;
    }

    public int getvX() {
        return vX;
    }

    public int getvY() {
        return vY;
    }
}

package advent.day13;

import java.math.BigInteger;

public class Machine {
    BigInteger axv;
    BigInteger bxv;
    BigInteger xp;

    BigInteger ayv;
    BigInteger byv;
    BigInteger yp;

    public Machine(long axv, long bxv, long xp, long ayv, long byv, long yp) {
        this.axv = BigInteger.valueOf(axv);
        this.bxv = BigInteger.valueOf(bxv);
        //        this.xp = BigInteger.valueOf(xp);
        this.xp = BigInteger.valueOf(10000000000000L + xp);
        this.ayv = BigInteger.valueOf(ayv);
        this.byv = BigInteger.valueOf(byv);
        //        this.yp = BigInteger.valueOf(yp);
        this.yp = BigInteger.valueOf(10000000000000L + yp);
    }

    public BigInteger a() {
        return (bxv.multiply(yp).subtract(byv.multiply(xp))).divide(bxv.multiply(ayv).subtract(byv.multiply(axv)));
    }

    public BigInteger b() {
        return (yp.subtract(ayv.multiply(a()))).divide(byv);
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

    public BigInteger cost() {
        if (!isValid()) {
            return BigInteger.ZERO;
        }
        return a().multiply(BigInteger.valueOf(3)).add(b());
    }

    public boolean isValid() {
        return a().multiply(axv).add(b().multiply(bxv)).equals(xp)
            && a().multiply(ayv).add(b().multiply(byv)).equals(yp);
    }
}

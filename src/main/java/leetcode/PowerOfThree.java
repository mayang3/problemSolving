package leetcode;

import java.math.BigInteger;

public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        double res = baseLog(n, 3);
        return res - (int)res == 0;
    }

    double baseLog(double x, double base) {
        return Math.log10(x) / Math.log10(base);
    }

    public static void main(String[] args) {
        PowerOfThree powerOfThree = new PowerOfThree();
        System.out.println(powerOfThree.isPowerOfThree(243));
    }
}

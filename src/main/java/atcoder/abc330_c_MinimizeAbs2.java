package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc330_c_MinimizeAbs2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long D = scanner.nextLong();
        long ans = Long.MAX_VALUE;


        // minimize |x^2 + y^2 - D|

        for (long x = 0; x <= (long) Math.sqrt(D); x++) {
            long C = x * x - D;

            if (C >= 0) {
                ans = Math.min(ans, Math.abs(x * x - D));
            } else {
                long y = (long) Math.ceil(Math.sqrt(-C));
                ans = Math.min(ans, Math.abs(x * x + y * y - D));
                y = (long) Math.floor(Math.sqrt(-C));
                ans = Math.min(ans, Math.abs(x * x + y * y - D));
            }
        }

        System.out.println(ans);

    }
}

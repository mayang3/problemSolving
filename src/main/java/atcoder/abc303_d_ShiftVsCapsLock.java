package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc303_d_ShiftVsCapsLock {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int X = scanner.nextInt();
        int Y = scanner.nextInt();
        int Z = scanner.nextInt();

        String S = scanner.next();

        long on = 1_000_000_000_000_000_000L;
        long off = 0;

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'A') {
                long t = on;
                on = Math.min(on + X, off + Z + X);
                off = Math.min(off + Y, t + Z + Y);
            } else {
                long t = off;
                off = Math.min(off + X, on + Z + X);
                on = Math.min(on + Y, t + Z + Y);
            }
        }

        System.out.println(Math.min(on, off));
    }
}

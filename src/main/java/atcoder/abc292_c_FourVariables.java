package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc292_c_FourVariables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        long ans = 0;
        for (int ab = 1; ab < N; ab++) {
            int cd = N - ab;

            long cnt1 = getCount(ab);
            long cnt2 = getCount(cd);

            ans += (cnt1 * cnt2);
        }

        System.out.println(ans);
    }

    static long getCount(int ab) {
        long cnt = 0;

        for (int a = 1; a <= (int)Math.sqrt(ab); a++) {
            if (ab % a == 0) {
                cnt++;

                if (ab != a * a) {
                    cnt++;
                }
            }
        }

        return cnt * 2;
    }
}

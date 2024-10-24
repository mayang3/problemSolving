package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc145_d_Knight {

    static final int MOD = 1000000007;
    static final int MX = 1000001; // 최대 크기 설정

    static long[] facs = new long[MX]; // 팩토리얼 저장
    static long[] facInvs = new long[MX]; // 역팩토리얼 저장

    // modular exponential
    static long modExp(long base, long power) {
        if (power == 0) {
            return 1;
        } else {
            long cur = modExp(base, power / 2);
            cur = (cur * cur) % MOD;
            if (power % 2 == 1) {
                cur = (cur * base) % MOD;
            }
            return cur;
        }
    }

    // modular inverse
    static long inv(long base) {
        return modExp(base, MOD - 2);
    }

    // modular multiply
    static long mul(long A, long B) {
        return (A * B) % MOD;
    }

    // binomial coefficient
    static long choose(long a, long b) {
        if (b > a) return 0;
        if (a < 0) return 0;
        if (b < 0) return 0;
        long cur = facs[(int) a];
        cur = mul(cur, facInvs[(int) b]);
        cur = mul(cur, facInvs[(int) (a - b)]);
        return cur;
    }

    static void initFacs() {
        facs[0] = 1;
        facInvs[0] = 1;
        for (int i = 1; i < MX; i++) {
            facs[i] = (facs[i - 1] * i) % MOD;
            facInvs[i] = inv(facs[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initFacs();

        long X = sc.nextLong();
        long Y = sc.nextLong();

        // (X + Y) % 3 != 0이면, 목표 좌표에 도달할 수 없으므로 0 출력
        if ((X + Y) % 3 != 0) {
            System.out.println(0);
            return;
        }

        // max(X, Y) > 2 * min(X, Y)이면 도달 불가능
        if (Math.max(X, Y) > 2 * Math.min(X, Y)) {
            System.out.println(0);
            return;
        }

        // X와 Y를 K로 나눈 후 각각 이동을 계산
        long n = (X + Y) / 3;
        long a = (2 * Y - X) / 3;
        long b = (2 * X - Y) / 3;

        System.out.println(choose(n, a));
    }
}

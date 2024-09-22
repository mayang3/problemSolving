package atcoder;

import java.util.Scanner;

/**
 * Keys
 * @author neo82
 */
public class abc356_c_keys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        int [][] KEYS = new int[M][N];
        String [] R = new String[M];

        for (int i = 0; i < M; i++) {
            int C = scanner.nextInt();

            for (int j = 0; j < C; j++) {
                int key = scanner.nextInt();
                KEYS[i][key - 1] = 1; // this is used
            }

            R[i] = scanner.next();
        }


        int ans = 0;

        for (int i = 0; i < (1 << N); i++) {
            boolean judge = true;

            // 현재 조합이 정의된 모든 테스트를 통과하는지를 확인한다.
            for (int j = 0; j < M; j++) {
                int usedKey = 0;

                for (int p = 0; p < N; p++) {
                    // (i & (1 << j)) != 0 는 현재 조합에서 실제 사용된 키를 의미한다. (즉, 현재 조합에서 1이 위치한 곳)
                    // 현재 조합에서 1 이 위치한곳은 실제 키를 가정한다.
                    // 테스트 케이스에서 이 키가 실제 사용되었다면, 가정된 실제 키가 사용된 것으로 카운트 된다.
                    if (KEYS[j][p] == 1 && (i & (1 << p)) != 0) {
                        usedKey++;
                    }
                }

                if (usedKey >= K && R[j].equals("x")) {
                    judge = false;
                    break;
                }

                if (usedKey < K && R[j].equals("o")) {
                    judge = false;
                    break;
                }
            }

            if (judge) {
                ans++;
            }
        }

        System.out.println(ans);

    }
}

package atcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc367_d_Pedometer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt() % M;
        }

        long[] prefixSum = new long[N * 2 - 1];

        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = (prefixSum[i - 1] + A[(i - 1) % N]) % M;
        }

        Map<Long, Integer> countMap = new HashMap<>();
        long ans = 0;

        for (int right = 0; right < prefixSum.length; right++) {
            int left = right - N + 1;

            if (left > 0) {
                countMap.merge(prefixSum[left - 1], -1, Integer::sum);
                if (countMap.get(prefixSum[left - 1]) == 0) {
                    countMap.remove(prefixSum[left - 1]);
                }
            }

            ans += countMap.getOrDefault(prefixSum[right], 0);


            if (right < N) {
                countMap.merge(prefixSum[right], 1, Integer::sum);
            }
        }


        System.out.println(ans);

    }
}

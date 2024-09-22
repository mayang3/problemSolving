package atcoder;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author neo82
 */
public class Solution {
    static Random r = new Random();
    public static void main(String[] args) {
        for (int j = 0; j < 1000; j++) {
            int N = 5;
            long[] A = new long[] {5, 22, 77, 89, 95};
            long totalSum = 0;

            TreeMap<Long, Integer> map = new TreeMap<>();

            for (int i = 0; i < N; i++) {
//                A[i] = ThreadLocalRandom.current().nextLong(1L, 100L);
                totalSum += A[i];
                map.merge(A[i], 1, Integer::sum);
            }

            long res1 = solve1(totalSum, N, A);
            long res2 = solve2(N, map, A);

            if (res1 != res2) {
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(Arrays.toString(A));
                break;
            }
        }

    }

    private static long solve1(long totalSum, int N, long[] A) {
        long target1 = totalSum / N;
        long target2 = target1 + 1;
        long targetCount2 = totalSum % N;
        long targetCount1 = N - targetCount2;

        long[] B = new long[N];
        Arrays.fill(B, target1);

        for (int i = 0; i < targetCount2; i++) {
            B[i] = target2;
        }

        Arrays.sort(A);
        Arrays.sort(B);

        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.abs(A[i] - B[i]);
        }

        return ans / 2;
    }

    static long solve2(int N, TreeMap<Long, Integer> map, long[] A) {
        for (int i = 0; i < N * 2; i++) {
            long min = map.firstKey();
            long max = map.lastKey();

            desc(map, min);
            desc(map, max);

            if ((min + max) % 2L == 0) {
                map.merge((min + max) / 2L, 1, Integer::sum);
                map.merge((min + max) / 2L, 1, Integer::sum);
            } else {
                map.merge((min + max) / 2L, 1, Integer::sum);
                map.merge(((min + max) / 2L) + 1, 1, Integer::sum);
            }
        }

        Arrays.sort(A);
        int i = 0;
        long[] B = Arrays.copyOf(A, A.length);

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                B[i++] = entry.getKey();
            }
        }

        int M = (N + 1) / 2;
        long ans = 0;

        for (int j = 0; j < M; j++) {
            ans += Math.abs(A[j] - B[j]);
        }

        return ans;
    }

    static void desc(TreeMap<Long, Integer> map, long val) {
        map.merge(val, -1, Integer::sum);

        if (map.get(val) == 0) {
            map.remove(val);
        }
    }
}

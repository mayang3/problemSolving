package atcoder;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author neo82
 */
public class abc305_d_SleepLog {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] A = new int[N];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
            treeMap.put(A[i], i);
        }

        long[] pSum = new long[N];

        for (int i = 2; i < N; i++) {
            if (i % 2 == 0) {
                pSum[i] = pSum[i - 1] + (A[i] - A[i - 1]);
            } else {
                pSum[i] = pSum[i - 1];
            }
        }

        int Q = scanner.nextInt();

        for (int i = 0; i < Q; i++) {
            int L = scanner.nextInt();
            int R = scanner.nextInt();

            Map.Entry<Integer, Integer> ceilingEntry = treeMap.ceilingEntry(L);
            Map.Entry<Integer, Integer> flooredEntry = treeMap.floorEntry(R);

            if (ceilingEntry == null || flooredEntry == null) {
                System.out.println(0);
                continue;
            }

            int leftIndex = ceilingEntry.getValue();
            int rightIndex = flooredEntry.getValue();

            if (leftIndex == rightIndex) {
                if (leftIndex == L) {
                    if (leftIndex % 2 == 0) {
                        System.out.println(0);
                    } else {
                        System.out.println(R - L);
                    }
                } else if (rightIndex == R) {
                    if (rightIndex % 2 != 0) {
                        System.out.println(0);
                    } else {
                        System.out.println(R - L);
                    }
                } else {
                    if (leftIndex % 2 == 0) {
                        System.out.println(A[leftIndex] - L);
                    } else {
                        System.out.println(R - A[leftIndex]);
                    }
                }

                continue;
            } else if (leftIndex > rightIndex) {
                if (leftIndex % 2 != 0) {
                    System.out.println(0);
                } else {
                    System.out.println(R - L);
                }

                continue;
            }


            long ans = pSum[rightIndex] - pSum[leftIndex];

            ans += leftIndex % 2 == 0 ? ceilingEntry.getKey() - L : 0;
            ans += rightIndex % 2 != 0 ? R - flooredEntry.getKey() : 0;

            System.out.println(ans);
        }
    }
}

package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc376_e_MaxSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        while (T-- > 0) {
            int N = scanner.nextInt();
            int K = scanner.nextInt();

            int[] A = new int[N];
            int[] B = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scanner.nextInt();
            }

            for (int i = 0; i < N; i++) {
                B[i] = scanner.nextInt();
            }

            List<int[]> C = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                C.add(new int[]{A[i], B[i]});
            }

            C.sort(Comparator.comparingInt(o -> o[0]));
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

            long sum = 0;
            long ans = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                int a = C.get(i)[0];
                int b = C.get(i)[1];

                if (pq.size() == K - 1) {
                    ans = Math.min(ans, a * (sum + b));
                }

                pq.add(b);
                sum += b;

                if (pq.size() > K - 1) {
                    sum -= pq.poll();
                }
            }

            System.out.println(ans);

        }
    }
}

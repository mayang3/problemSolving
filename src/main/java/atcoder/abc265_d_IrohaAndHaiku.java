package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc265_d_IrohaAndHaiku {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        long P = scanner.nextLong();
        long Q = scanner.nextLong();
        long R = scanner.nextLong();

        int[] A = new int[N];
        TreeSet<Long> treeSet = new TreeSet<>();
        treeSet.add(0L);

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
            treeSet.add(treeSet.last() + A[i]);
        }

        for (long sum : treeSet) {
            if (treeSet.contains(sum + P) && treeSet.contains(sum + P + Q) && treeSet.contains(sum + P + Q + R)) {
                System.out.println("Yes");
                return;
            }
        }

        System.out.println("No");

    }
}

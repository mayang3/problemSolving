package atcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc355_d_IntersectingIntervals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();


        List<Pair> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();

            list.add(new Pair(l, r));
        }

        Collections.sort(list, (o1, o2) -> o1.l == o2.l ? o2.r - o1.r : o1.l - o2.l);

        long ans = 0;

        for (int i = 0; i < N-1; i++) {
            Pair p = list.get(i);

            int j = binarysearch(list, p.r, i + 1, list.size() - 1);

            if (j >= 0) {
                ans += (j - i);
            }
        }

        System.out.println(ans);
    }

    private static int binarysearch(List<Pair> list, int val, int left, int right) {

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid).l <= val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    static class Pair {
        int l;
        int r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}

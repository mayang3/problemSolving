package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc346_c_Sigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = scanner.nextInt();

            if (0 < num && num < K + 1) {
                list.add(num);
            }
        }

        list.add(0);
        list.add(K+1);

        Collections.sort(list);

        long sum = 0;

        for (int i = 1; i < list.size(); i++) {
            int diff = list.get(i) - list.get(i - 1);

            if (diff <= 1) {
                continue;
            }

            if (diff == 2) {
                sum += ((long) list.get(i-1) + 1);
            } else {
                sum += ((((long) list.get(i - 1) + 1) + (list.get(i) - 1)) * (diff - 1) / 2L);
            }
        }

        System.out.println(sum);
    }
}

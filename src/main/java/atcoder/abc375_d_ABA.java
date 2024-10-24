package atcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc375_d_ABA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String S = scanner.next();

        Map<Integer, Long> countMap = new HashMap<>();
        Map<Integer, Long> lengthMap = new HashMap<>();

        long ans = 0;

        for (int i = 2; i < S.length(); i++) {
            int before = S.charAt(i - 2) - 'A';
            int here = S.charAt(i) - 'A';

            countMap.merge(before, 1L, Long::sum);
            lengthMap.merge(before, i - 2L, Long::sum);

            if (countMap.containsKey(here)) {
                long count = countMap.get(here);
                long len = lengthMap.get(here);

                ans += (i * count - len - count);
            }
        }

        System.out.println(ans);
    }
}

package atcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc321_c_321LikeSearcher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int K = scanner.nextInt();

        List<Long> ans = new ArrayList<>();

        for (int i = 2; i < (1 << 10); i++) {
            long x = 0;

            for (int j = 9; j >= 0; j--) {
                if ((i & (1 << j)) > 0) {
                    x *= 10;
                    x += j;
                }
            }

            ans.add(x);
        }

        Collections.sort(ans);

        System.out.println(ans.get(K - 1));
    }
}
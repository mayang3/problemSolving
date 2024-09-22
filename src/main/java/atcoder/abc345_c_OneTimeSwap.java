package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc345_c_OneTimeSwap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String S = scanner.next();

        Map<Character, Integer> countMap = new HashMap<>();

        for (int i = 0; i < S.length(); i++) {
            countMap.merge(S.charAt(i), 1, Integer::sum);
        }

        List<Integer> values = new ArrayList<>(countMap.values());

        long[] prefix = new long[values.size()];

        // 2회 이상 출현한 문자가 하나라도 있으면, 최초 제시된 문자 그대로가 답이 될 수 있기 때문에 +1 을 해준다.
        // 만약 2회 이상 출현한 문자가 여러개라 할지라도, 똑같은 문자는 한번만 카운트 되기 때문에 여전히 + 1 이 된다.
        boolean same = false;

        for (int i = values.size() - 1; i >= 0; i--) {
            if (values.get(i) > 1) {
                same = true;
            }

            if (i == values.size() - 1) {
                prefix[i] = values.get(i);
            } else {
                prefix[i] = prefix[i + 1] + values.get(i);
            }
        }

        long ans = 0;

        for (int i = 0; i < values.size() - 1; i++) {
            int here = values.get(i);

            ans += (here * prefix[i + 1]);
        }

        if (same) {
            ans++;
        }

        System.out.println(ans);
    }
}

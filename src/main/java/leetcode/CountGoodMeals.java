package leetcode;

import java.util.HashMap;
import java.util.Map;

public class CountGoodMeals {
    static int MOD = (int)1e9 + 7;

    public int countPairs(int[] deliciousness) {
        int [] pow = new int[22];

        for (int i = 0; i < pow.length; i++) {
            pow[i] = (int)Math.pow(2, i);
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < deliciousness.length; i++) {
            map.merge(deliciousness[i], 1, Integer::sum);
        }

        int output = 0;

        for (int i = 0; i < deliciousness.length; i++) {
            map.merge(deliciousness[i], -1, Integer::sum);

            if (map.get(deliciousness[i]) == 0) {
                map.remove(deliciousness[i]);
            }

            for (int j = 0; j < pow.length; j++) {
                int find = pow[j] - deliciousness[i];

                if (map.containsKey(find)) {
                    output = (output % MOD + map.get(find) % MOD) % MOD;
                }
            }
        }

        return output;
    }

    public int countPairsNaive(int[] deliciousness) {
        int [] pow = new int[21];

        for (int i = 0; i < pow.length; i++) {
            pow[i] = (int)Math.pow(2, i+1);
        }

        return 0;

    }

    public static void main(String[] args) {
        int [] d = {149,107,1,63,0,1,6867,1325,5611,2581,39,89,46,18,12,20,22,234};

        CountGoodMeals countGoodMeals = new CountGoodMeals();

        System.out.println(countGoodMeals.countPairs(d));
    }
}

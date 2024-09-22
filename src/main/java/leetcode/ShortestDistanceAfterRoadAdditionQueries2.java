package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neo82
 */
public class ShortestDistanceAfterRoadAdditionQueries2 {
    public static void main(String[] args) {
        ShortestDistanceAfterRoadAdditionQueries2 queries2 = new ShortestDistanceAfterRoadAdditionQueries2();

        int[][] quries = {{2,4},{0,2},{0,4}};

        System.out.println(Arrays.toString(queries2.shortestDistanceAfterQueries(5, quries)));
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n-1; i++) {
            map.put(i, i + 1);
        }

        int [] ans = new int[queries.length];
        int i = 0;

        for (int[] q : queries) {
            int start = q[0];
            int end = q[1];

            if (!map.containsKey(start) || map.get(start) >= end) {
                ans[i++] = map.size();
                continue;
            }

            int j = map.get(start);

            while (j < end) {
                j = map.remove(j);
            }

            map.put(start, end);
            ans[i++] = map.size();

        }

        return ans;
    }

}

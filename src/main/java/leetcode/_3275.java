package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author neo82
 */
public class _3275 {
    public static void main(String[] args) {
        int [][] queries = {{1,2},{3,4},{2,3},{-3,0}};
        int k = 2;

        _3275 a = new _3275();
        System.out.println(Arrays.toString(a.resultsArray(queries, k)));
    }

    public int[] resultsArray(int[][] queries, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int[] ans = new int[queries.length];
        int i = 0;

        for (int[] q : queries) {
            pq.add(Math.abs(q[0]) + Math.abs(q[1]));

            if (pq.size() < k) {
                ans[i++] = -1;
                continue;
            }

            while (!pq.isEmpty() && pq.size() > k) {
                pq.poll();
            }

            ans[i++] = pq.peek();
        }

        return ans;
    }
}

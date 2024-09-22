package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;

/**
 * @author neo82
 */
public class MostProfitAssigningWork {

    public static void main(String[] args) {
        int [] difficulty = {68,35,52,47,86};
        int [] profit = {67,17,1,81,3};
        int [] worker = {92,10,85,84,82};

        MostProfitAssigningWork mostProfitAssigningWork = new MostProfitAssigningWork();
        System.out.println(mostProfitAssigningWork.maxProfitAssignment(difficulty, profit, worker));
    }


    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        int N = difficulty.length;

        Pair[] pairs = new Pair[N];

        for (int i = 0; i < N; i++) {
            pairs[i] = new Pair(difficulty[i], profit[i]);
        }

        Arrays.sort(pairs, Comparator.comparingInt(o -> o.difficulty));

        int max = 0;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, pairs[i].profit);
            pairs[i].profit = max;
        }

        int ans = 0;

        for (int i = 0; i < worker.length; i++) {
            ans += binarysearch(pairs, worker[i]);
        }

        return ans;
    }

    private int binarysearch(Pair[] pairs, int value) {
        int left = 0;
        int right = pairs.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (pairs[mid].difficulty <= value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (right < 0) {
            return 0;
        }

        return pairs[right].profit;
    }


    static class Pair {
        int difficulty;
        int profit;

        public Pair(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }
}

package leetcode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author neo82
 */
public class RandomPointInNonOverlappingRectangles {

    static class Solution {
        // pSum은 사각형의 면적을 계산한 것이 아니라, 각 사각형에 포함된 정수 좌표들의 개수를 누적한 값이다.

        long[] pSum;
        int[][] rects;
        ThreadLocalRandom random = ThreadLocalRandom.current();

        public Solution(int[][] rects) {
            int N = rects.length;
            this.rects = rects;
            this.pSum = new long[N];

            for (int i = 0; i < N; i++) {
                if (i > 0) {
                    pSum[i] = pSum[i - 1];
                }

                int a = rects[i][0];
                int b = rects[i][1];
                int x = rects[i][2];
                int y = rects[i][3];

                pSum[i] += ((long) x - a + 1) * ((long) y - b + 1);
            }
        }

        public int[] pick() {
            int N = this.rects.length;

            int[] rect = this.rects[binarySearch(this.pSum, this.random.nextLong(0L, this.pSum[N - 1] + 1))];

            int a = rect[0];
            int b = rect[1];
            int x = rect[2];
            int y = rect[3];

            return new int[]{this.random.nextInt(a, x + 1), this.random.nextInt(b, y + 1)};
        }

        private int binarySearch(long[] pSum, long target) {
            int left = 0;
            int right = pSum.length;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (target <= pSum[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }
}

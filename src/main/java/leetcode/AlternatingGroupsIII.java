package leetcode;

import java.util.List;

/**
 * @author neo82
 */
public class AlternatingGroupsIII {
    public List<Integer> numberOfAlternatingGroups(int[] colors, int[][] queries) {
        return null;
    }


    static class SegmentTree {
        SegmentTree left;
        SegmentTree right;

        int min;
        int max;
        int len;

        public SegmentTree(int[] colors, int min, int max) {


        }

        public int initialize(int[] colors, int min, int max) {
            int mid = (min + max) / 2;

            int l = mid - 1;
            int r = mid;
            int len = 0;

            if (colors[l] != colors[r]) {
                // left
                while (l > min && colors[l] != colors[l - 1]) {
                    l--;
                }

                // right
                while (r < max && colors[r] != colors[r + 1]) {
                    r++;
                }

                len = r - l + 1;
            }

            int leftMax = initialize(colors, min, mid);
            int rightMax = initialize(colors, mid + 1, max);

            this.len = Math.max(len, Math.max(leftMax, rightMax));

            return -1;
        }


    }
}

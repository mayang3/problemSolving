package leetcode;

import java.util.*;

public class ShortestDistanceToTargetColor {
        public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
            Map<Integer, TreeSet<Integer>> colorMap = new HashMap<>();

            for (int i = 0; i < colors.length; i++) {
                colorMap.computeIfAbsent(colors[i], t -> new TreeSet<>()).add(i);
            }

            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < queries.length; i++) {
                TreeSet<Integer> treeSet = colorMap.get(queries[i][1]);

                if (treeSet == null || treeSet.isEmpty()) {
                    res.add(-1);
                    continue;
                }

                Integer found1 = treeSet.floor(queries[i][0]);
                Integer found2 = treeSet.ceiling(queries[i][0]);

                if (found1 == null && found2 == null) {
                    res.add(-1);
                } else if (found1 == null) {
                    res.add(Math.abs(queries[i][0] - found2));
                } else if (found2 == null) {
                    res.add(Math.abs(queries[i][0] - found1));
                } else {
                    res.add(Math.min(Math.abs(queries[i][0] - found1), Math.abs(queries[i][0] - found2)));
                }
            }

            return res;
        }

    public static void main(String[] args) {
        int [] colors = {2};
        int [][] queries = {{0,3}};

        ShortestDistanceToTargetColor color = new ShortestDistanceToTargetColor();
        System.out.println(color.shortestDistanceColor(colors, queries));
    }
}

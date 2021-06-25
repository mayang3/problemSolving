package leetcode;

import java.util.*;

public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        int height = wall.size();
        int max = 0;

        Map<Integer, Integer> countMap = new HashMap<>();

        for (List<Integer> sub : wall) {
            int sum = 0;

            for (int i = 0; i < sub.size()-1; i++) {
                sum += sub.get(i);
                countMap.merge(sum, 1, Integer::sum);
                max = Math.max(countMap.getOrDefault(sum, 0), max);
            }
        }

        return height - max;
    }

    public static void main(String[] args) {
        List<List<Integer>> wall = new ArrayList<>();

        wall.add(Arrays.asList(new Integer [] {1,1,3,1}));
        wall.add(Arrays.asList(new Integer [] {2,2,2}));
        wall.add(Arrays.asList(new Integer [] {1,3,2}));

        BrickWall brickWall = new BrickWall();
        System.out.println(brickWall.leastBricks(wall));
    }
}

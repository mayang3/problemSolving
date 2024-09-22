package leetcode;

import java.util.*;

/**
 * @author neo82
 */
public class SelectCellsInGridWithMaximumScore {
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();

        // [[16,18],[20,20],[18,18],[1,15]]
        lists.add(Arrays.asList(16, 18));
        lists.add(Arrays.asList(20, 20));
        lists.add(Arrays.asList(18, 18));
        lists.add(Arrays.asList(1, 15));

        SelectCellsInGridWithMaximumScore selectCellsInGridWithMaximumScore = new SelectCellsInGridWithMaximumScore();
        System.out.println(selectCellsInGridWithMaximumScore.maxScore(lists));
    }

    public int maxScore(List<List<Integer>> grid) {

        Map<Integer, List<Integer>> indexesMap = new HashMap<>();

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                indexesMap.computeIfAbsent(grid.get(i).get(j), t -> new ArrayList<>()).add(i);
            }
        }

        List<Map.Entry<Integer, List<Integer>>> list = new ArrayList<>(indexesMap.entrySet());

        Map<String, Integer> dp = new HashMap<>();

        return solve(dp, list, 0, 0);
    }

    private int solve(Map<String, Integer> dp, List<Map.Entry<Integer, List<Integer>>> list, int i, int mask) {
        if (i >= list.size()) {
            return 0;
        }

        String key = i + "_" + mask;

        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int res = solve(dp, list, i + 1, mask);

        Map.Entry<Integer, List<Integer>> here = list.get(i);

        for (int j = 0; j < here.getValue().size(); j++) {
            int index = here.getValue().get(j);

            if ((mask & (1 << index)) == 0) {
                res = Math.max(res, solve(dp, list, i + 1, (mask | (1 << index))) + here.getKey());
            }
        }

        dp.put(key, res);

        return res;
    }
}

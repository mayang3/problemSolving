package leetcode;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();

        boolean [] visited = new boolean[graph.length];

        dfs(res, new ArrayList<>(), graph, visited, 0);

        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int [][] graph, boolean[] visited, int i) {
        path.add(i);
        visited[i] = true;


        if (i < graph.length - 1) {
            for (int adj = 0; adj < graph[i].length; adj++) {
                int there = graph[i][adj];

                if (visited[there] == false) {
                    dfs(res, path, graph, visited, there);
                }
            }
        }

        if (i == graph.length - 1) {
            res.add(new ArrayList<>(path));
        }

        path.remove(path.size() - 1);
        visited[i] = false;
    }

    public static void main(String[] args) {
        int [][] graph = {{1,2},{3},{3},{}};

        AllPathsFromSourceToTarget allPathsFromSourceToTarget = new AllPathsFromSourceToTarget();
        System.out.println(allPathsFromSourceToTarget.allPathsSourceTarget(graph));
    }
}

package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc317_c_RememberingDays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        Map<Integer, List<Pair>> graph = new HashMap<>();

        for (int i = 0; i < M; i++) {
            int A = scanner.nextInt() - 1;
            int B = scanner.nextInt() - 1;
            int C = scanner.nextInt();

            graph.computeIfAbsent(A, t-> new ArrayList<>()).add(new Pair(B, C));
            graph.computeIfAbsent(B, t-> new ArrayList<>()).add(new Pair(A, C));
        }



        int ans = 0;

        boolean [] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            ans = Math.max(ans, dfs(graph, i, visited));
            visited[i] = false;
        }

        System.out.println(ans);
    }

    private static int dfs(Map<Integer, List<Pair>> graph, int i, boolean[] visited) {
        int max = 0;

        if (graph.containsKey(i)) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                Pair adj = graph.get(i).get(j);

                if (visited[adj.v] == false) {
                    visited[adj.v] = true;
                    max = Math.max(max, adj.cost + dfs(graph, adj.v, visited));
                    visited[adj.v] = false;
                }
            }
        }

        return max;
    }

    static class Pair {
        int v;
        int cost;

        public Pair(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}

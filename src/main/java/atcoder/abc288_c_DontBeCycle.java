package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc288_c_DontBeCycle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[N];

        int S = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i, visited, graph);
                S++;
            }
        }

        // M(주어진 엣지 - 최대엣지) - L(MST 를 유지 가능한 엣지의 수)
        // 여기서 L = N + S 가 된다.
        System.out.println(M - N + S);

    }

    private static void dfs(int i, boolean[] visited, Map<Integer, List<Integer>> graph) {
        visited[i] = true;

        for (int adj : graph.get(i)) {
            if (!visited[adj]) {
                dfs(adj, visited, graph);
            }
        }
    }
}

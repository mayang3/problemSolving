package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc350_d_NewFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;

            graph.computeIfAbsent(u, t -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, t -> new ArrayList<>()).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        long ans = 0;

        for (int i = 0; i < N; i++) {
            if (!visited.contains(i)) {
                Pair pair = solve(visited, graph, i);

                if (pair.vertices > 2) {
                    ans += (getAllEdges(pair.vertices) - ((long)pair.edges / 2L));
                }
            }
        }

        System.out.println(ans);
    }

    private static long getAllEdges(int n) {
        return (long)n * (n - 1) / 2L;
    }

    private static Pair solve(Set<Integer> visited, Map<Integer, List<Integer>> graph, int i) {
        visited.add(i);

        int vertices = 1;
        int edges = 0;

        if (graph.containsKey(i)) {
            for (int adjacent : graph.get(i)) {
                if (!visited.contains(adjacent)) {
                    Pair another = solve(visited, graph, adjacent);

                    vertices += another.vertices;
                    edges += another.edges;
                }

                edges += 1;
            }
        }

        return new Pair(vertices, edges);
    }

    static class Pair {
        int vertices;
        int edges;

        public Pair(int vertices, int edges) {
            this.vertices = vertices;
            this.edges = edges;
        }
    }
}

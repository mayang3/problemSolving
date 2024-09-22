package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc340_d_SuperTakahashiBros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        Map<Integer, List<Pair>> graph = new HashMap<>();

        for (int i = 0; i < N - 1; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            int C = scanner.nextInt() - 1;

            graph.computeIfAbsent(i, t -> new ArrayList<>()).add(new Pair(i + 1, A));
            graph.computeIfAbsent(i, t -> new ArrayList<>()).add(new Pair(C, B));
        }

        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.cost == o2.cost) return 0;
            return o1.cost < o2.cost ? -1 : 1;
        });

        pq.add(new Pair(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Pair here = pq.poll();

            int u = here.v;
            long cost = here.cost;

            if (dist[u] < cost) {
                continue;
            }

            if (u == N - 1) {
                dist[u] = Math.min(dist[u], cost);
            }

            if (graph.containsKey(u)) {
                for (Pair adj : graph.get(u)) {
                    int v = adj.v;
                    long newCost = cost + adj.cost;

                    if (newCost < dist[v]) {
                        pq.add(new Pair(v, newCost));
                        dist[v] = newCost;
                    }
                }
            }
        }

        System.out.println(dist[N - 1]);
    }

    static class Pair {
        int v;
        long cost;


        public Pair(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}

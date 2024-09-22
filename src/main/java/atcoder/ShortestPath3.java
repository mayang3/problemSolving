package atcoder;

import java.util.*;

/**
 * 다익스트라는 기본적으로 시작점에서 모든 정점의 최단구간을 구하는 알고리즘이다.
 *
 * v 정점에는 최단거리의 단 한번만을 방문하기 때문에, dist[v] 는 항상 최단거리임을 보장한다.
 *
 * @author neo82
 */
public class ShortestPath3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int [] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        Map<Integer, List<Pair>> graph = new HashMap<>();

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            int w = scanner.nextInt();

            graph.computeIfAbsent(u, t-> new ArrayList<>()).add(new Pair(v, w));
            graph.computeIfAbsent(v, t-> new ArrayList<>()).add(new Pair(u, w));
        }

        long [] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));
        pq.add(new Pair(0, A[0]));

        while (!pq.isEmpty()) {
            Pair here = pq.poll();

            int u = here.v;
            long w1 = here.weight;

            if (dist[u] < w1) {
                continue;
            }

            for (Pair adj : graph.get(u)) {
                int v = adj.v;
                long w2 = adj.weight + w1 + A[v];

                if (dist[v] > w2) {
                    pq.add(new Pair(v, w2));
                    dist[v] = w2;
                }
            }
        }

        for (int i = 1; i < N; i++) {
            System.out.print(dist[i] + " ");
        }
    }


    static class Pair {
        int v;
        long weight;

        public Pair(int v, long weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}

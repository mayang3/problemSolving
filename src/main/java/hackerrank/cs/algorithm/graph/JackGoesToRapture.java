package hackerrank.cs.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class JackGoesToRapture {
    public static void getCost(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        Map<Integer, List<Node>> graph = new HashMap<>();

        for (int i = 0; i < gFrom.size(); i++) {
            graph.computeIfAbsent(gFrom.get(i)-1, t -> new ArrayList<>()).add(new Node(gTo.get(i)-1, gWeight.get(i)));
            graph.computeIfAbsent(gTo.get(i)-1, t -> new ArrayList<>()).add(new Node(gFrom.get(i)-1, gWeight.get(i)));
        }

        long [] dist = new long[gNodes];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o2.cost == o1.cost) {
                return 0;
            }
            return o1.cost < o2.cost ? -1 : 1;
        });

        pq.add(new Node(0, 0));

        while (pq.isEmpty() == false) {
            Node here = pq.poll();

            int u = here.v;
            long cost = here.cost;

            if (dist[u] < cost || graph.containsKey(u) == false) {
                continue;
            }

            for (int i = 0; i < graph.get(u).size(); i++) {
                Node there = graph.get(u).get(i);

                int v = there.v;
                long thereCost = there.cost;
                long nextDistance = dist[u] + Math.max(0, thereCost - dist[u]);

                if (nextDistance < dist[v]) {
                    dist[v] = nextDistance;
                    pq.add(new Node(v, nextDistance));
                }
            }
        }

        long res = dist[gNodes-1];

        if (res == Long.MAX_VALUE) {
            System.out.println("NO PATH EXISTS");
        } else {
            System.out.println(res);
        }
    }

    static class Node {
        int v;
        long cost;

        public Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        getCost(gNodes, gFrom, gTo, gWeight);

        bufferedReader.close();
    }
}

package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc376_d_Cycle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();


        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt() - 1;
            int b = scanner.nextInt() - 1;

            graph.get(a).add(b);
        }

        Set<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited.add(0);

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = Integer.valueOf(queue.size());

            for (int i = 0; i < size; i++) {
                int here = queue.poll();

                for (int adj : graph.get(here)) {
                    if (adj == 0) {
                        System.out.println(ans + 1);
                        return;
                    }

                    if (!visited.contains(adj)) {
                        queue.add(adj);
                        visited.add(adj);
                    }
                }
            }

            ans++;
        }

        System.out.println(-1);
    }
}

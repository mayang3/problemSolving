package hackerrank.cs.algorithm.graph;

import java.util.*;

/**
 */
public class ShortestReach {
	static int [][] adj;

	static int[] bfs(int n, int m, int[][] edges, int s) {

		adj = new int[n+1][n+1];

		for (int i=0 ; i<m ; i++) {
			adj[edges[i][0]][edges[i][1]] = 1;
			adj[edges[i][1]][edges[i][0]] = 1;
		}

		int [] discovered = new int[n+1];
		int [] cost = new int[n+1];
		cost[s] = 0;

		Queue<Integer> q = new LinkedList<>();
		q.add(s);

		discovered[s] = 1;

		while(q.isEmpty() == false) {
			int here = q.poll();

			for (int there=1 ; there<n+1 ; there++) {
				if (here == there) {
					continue;
				}

				if (discovered[there] == 1) {
					continue;
				}

				if (adj[here][there] == 1) {
					cost[there] = cost[here] + 6;
					q.add(there);
					discovered[there] = 1;
				} else if (adj[here][there] == 0) {
					cost[there] = -1;
				}
			}
		}

		// 원래 1을 더 크게 설정했던 부분과, 자기 자신의 인덱스를 제외한다. (2개)
		int [] ret = new int[cost.length - 2];

		int j = 0;

		for (int i=0 ; i<cost.length ; i++) {
			if (cost[i] != 0) {
				ret[j++] = cost[i];
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for(int a0 = 0; a0 < q; a0++){
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] edges = new int[m][2];
			for(int edges_i = 0; edges_i < m; edges_i++){
				for(int edges_j = 0; edges_j < 2; edges_j++){
					edges[edges_i][edges_j] = in.nextInt();
				}
			}
			int s = in.nextInt();
			int[] result = bfs(n, m, edges, s);
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
			}
			System.out.println("");


		}
		in.close();
	}
}

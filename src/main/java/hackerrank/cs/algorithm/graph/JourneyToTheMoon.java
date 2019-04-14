package hackerrank.cs.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 */
public class JourneyToTheMoon {

	static List<Integer> [] adj;
	static int N;

	static boolean [] visited;

	static long journeyToMoon(int n) {
		N = n;
		visited = new boolean[n];

		List<Integer> setSizeList = new ArrayList<>();

		for (int i=0 ; i<N ; i++) {
			if (visited[i] == false) {
				setSizeList.add(dfs(i, 1));
			}
		}


		// ** PATTERN **
		// old answer + the sum of old values x new value.
		long sum = 0;
		long result = 0;

		for (long size : setSizeList) {
			result += sum * size;
			sum += size;
		}

		return result;
	}


	static int dfs(int here, int count) {
		visited[here] = true;

		for (int i=0 ; i<adj[here].size() ; i++) {
			int there = adj[here].get(i);

			if (!visited[there]) {
				count = Math.max(count, dfs(there, ++count));
			}
		}

		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		adj = new List[n];

		for (int i=0 ; i<adj.length ; i++) adj[i] = new ArrayList<>();

		int p = in.nextInt();
		for(int i = 0; i < p; i++){
			int x = in.nextInt();
			int y = in.nextInt();

			adj[x].add(y);
			adj[y].add(x);
		}
		long result = journeyToMoon(n);
		System.out.println(result);
		in.close();

	}
}

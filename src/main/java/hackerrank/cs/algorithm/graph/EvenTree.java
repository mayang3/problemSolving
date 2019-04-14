package hackerrank.cs.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 기본 개념 :
 *
 * 각 부모 노드마다 자식들의 트리의 깊이에 따른 갯수가 "짝수" 라면 해당 Sub Tree 는 모두 잘라버리면 된다.
 *
 * 그렇게 해서 잘린 숫자가 output 이 된다.
 *
 */
public class EvenTree {

	static List<Integer>[] adj;
	static boolean [] visited;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int E = scanner.nextInt();

		adj = new List[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;

			adj[v].add(u);
		}

		Res res = dfs(0);

		System.out.println(res.removeCount);
	}

	static Res dfs(int here) {
		visited[here] = true;

		List<Res> resList = new ArrayList<>();

		int totalRemoveCount = 0;
		int totalChildCount = 0;
		boolean existEven = false;

		for (int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);

			if (visited[there] == false) {
				Res r = dfs(there);

				totalRemoveCount += r.removeCount;
				totalChildCount += (++r.childCount);

				if (r.childCount % 2 == 0) {
					existEven = true;
				}

				resList.add(r);
			}
		}

		if (existEven) {
			for (Res r : resList) {
				if (r.childCount % 2 == 0) {
					totalRemoveCount++;
					totalChildCount -= r.childCount;
				}
			}
		}

		return new Res(totalRemoveCount, totalChildCount);
	}

	/**
	 * 각 노드마다 자신의 removeCount 와 childCount 를 가지고 있는다.
	 */
	static class Res {
		int removeCount;
		int childCount;

		Res(int removeCount, int childCount) {
			this.removeCount = removeCount;
			this.childCount = childCount;
		}
	}
}

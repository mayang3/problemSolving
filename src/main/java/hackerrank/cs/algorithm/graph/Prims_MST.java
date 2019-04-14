package hackerrank.cs.algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/primsmstsub/problem
 *
 * kruskal 알고리즘과 prim 알고리즘의 가장 큰 차이는 진행방식이다.
 *
 * prim 알고리즘에서는 하나의 시작점으로 구성된 트리에 간선을 하나씩 추가하면서 스패닝 트리가 될때까지 키워 간다.
 *
 * 따라서 선택된 간선들은 중간 과정에서도 항상 연결된 트리를 이루게 된다.
 */
@SuppressWarnings("Duplicates")
public class Prims_MST {

	static class Prim {
		// vertex 의 개수
		final int v;
		// prim 알고리즘의 수행결과로 생성된 MST 를 저장하기 위한 배열
		final int [] parent;
		// 최소 가중치
		// 이 값을 저장하여 최소 가중치보다 작은 간선을 찾은 경우에만 업데이트 한다.
		final int [] minWeight;
		// 정점들이 MST 에 포함되어 있는지 여부를 나타내는 boolean 집합
		final boolean [] mstSet;

		Prim(int v) {
			this.v = v;
			this.parent = new int[v];
			this.minWeight = new int[v];
			this.mstSet = new boolean[v];

			// minWeight 값을 무한대로 초기화
			Arrays.fill(this.minWeight, Integer.MAX_VALUE);
		}

		/**
		 * 그래프의 모든 정점을 순회하면서,
		 * 현재 MST 에 포함되지 않은 정점들 중에 최소 가중치를 가진 정점의 번호를 가져온다.
		 * @return
		 */
		int minKey() {
			int min = Integer.MAX_VALUE;
			int minIdx = -1;

			for (int v = 0; v < this.v; v++) {
				if (mstSet[v] == false && minWeight[v] < min) {
					min = minWeight[v];
					minIdx = v;
				}
			}

			return minIdx;
		}

		int primMST(List<Pair> [] adj, int start) {
			minWeight[start] = 0;
			parent[start] = -1;

			for (int count = 0; count < this.v - 1; count++) {
				// 현재 MST 에 연결할 minWeight 를 가진 정점 가져온다.
				int u = minKey();
				// 해당 정점을 MST 에 포함되었음으로 마킹한다.
				this.mstSet[u] = true;

				// 정점 u 에 인접한 정점들 v 리스트들을 순회하면서 
				for (int i = 0; i < adj[u].size(); i++) {
					Pair there = adj[u].get(i);

					int v = there.v;
					int weight = there.c;

					if (mstSet[v] == false && weight < minWeight[v]) {
						this.parent[v] = u;
						this.minWeight[v] = weight;
					}
				}
			}

			int sum = 0;

			for (int i = 0; i < parent.length; i++) {
				sum += parent[i];
			}

			return sum;
		}
	}

	static class Pair {
		int v;
		int c;

		Pair(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int v = scanner.nextInt();
		int e = scanner.nextInt();

		List<Pair> [] adj = new List[v];

		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < e; i++) {
			int x = scanner.nextInt()-1;
			int y = scanner.nextInt()-1;
			int weight = scanner.nextInt();

			adj[x].add(new Pair(y, weight));
			adj[y].add(new Pair(x, weight));
		}

		int start = scanner.nextInt()-1;

		Prim prim = new Prim(v);
		System.out.println(prim.primMST(adj, start));
	}
}

package hackerrank.cs.algorithm.search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/cut-the-tree/problem
 *
 * You're given a tree.
 *
 * You have to tell the minimum weight difference after removing an edge in the the tree,
 *
 * such that, the difference between the weights of both of the partitions is as close as possible.
 * ( 즉, 두 파티션의 가중치의 합은 최소여야 한다.)
 *
 * Naive Solution :
 * 모든 엣지를 하나씩 지우면서 dfs 를 계속 호출하는 방법. O(N^2) 이 나오므로 모든 TC 를 통과하지 못한다.
 *
 * Actual Solution :
 *
 * Store sum of numbers on all the vertices and call it W.
 * 모든 정점들의 합을 저장하고 이것을 W 라고 부르자.
 *
 * Now suppose the weight of one part is P1, then the weight of other part will be W-P1.
 * 한 부분의 가중치를 P1 이라고 두면, 나머지 부분의 가중치는 W-P1 이다.
 *
 * So main task will be finding weight of all possible one parts in O(N) times.
 * 때문에 할일은 모든 가능한 한 부분만 O(N) 시간에 찾으면 된다.
 *
 * To do that, task any node(R) as the root of the tree, and call a DFS on that node.
 * 이것을 하기위해 root node 를 DFS 로 순회한다.
 *
 * This DFS will store weight of sub tree at each of the node.
 * 이 DFS 는 각 노드들의 subtree 의 가중치를 저장한다.
 *
 * Later, Suppose you remove an edge a b from the Tree (given vertex a is closer to root R).
 * 그 후에, 당신이 edge a to b 를 Tree 에서 삭제했다고 가정해보자. (주어진 정점 a 는 root R 과 연결된 정점)
 *
 * The you will get two trees, one of them will be rooted at vertex b.
 * 당신은 두개의 트리를 얻을 것이다. 그리고 그들중 한개인 정점 b 는 또다른 root 가 될것이다.
 *
 * We can get total weight of this part in O(1) time by DFS.
 * 우리는 DFS 로부터 상수시간에 이 부분의 모든 가중치를 얻을 수 있다.
 *
 * Weight of the other part can be calculated as we know the total weight W.
 * 다른 부분의 가중치는 total weight W 로부터 얻어낸다.
 *
 * So for all edges we can check the difference and report the minimum in O(N) time.
 * 모든 간선들에 대해 최소차를 확인할 수 있다.
 *
 *
 *
 */
public class CutTheTree {

	static boolean [] visited = new boolean[100009];
	static List<Integer>[] adj = new List[100009];
	// 두 트리의 diff 가 저장되는 배열
	// 이 배열에 저장되는 값들중 최소값이 정답이다.
	static int [] val_node = new int[100009];
	static int [] val = new int[100009];

	static {
		for (int i = 0; i < 100009; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	static int diff(int val, int sum) {
		return Math.abs(sum - 2 * val);
	}

	static int dfs(int node) {
		if (visited[node]) {
			return 0;
		}

		if (adj[node].size() == 1) {
			val_node[node] = val[node];
			visited[node] = true;
			return val_node[node];
		} else {
			visited[node] = true;

			int c = val[node];

			for (int i = 0; i < adj[node].size(); i++) {
				Integer there = adj[node].get(i);

				if (visited[there] == false) {
					c += dfs(there);
				}
			}

			val_node[node] = c;
			return val_node[node];
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		// 1. 데이터들의 총합인 s 를 미리 구한다.
		int s = 0;

		// 2. 데이터 개별 값도 저장
		for (int i = 1; i <= n; i++) {
			val[i] = scanner.nextInt();
			s = s + val[i];
		}

		// 3. graph 연결
		for (int i = 0; i < n - 1; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();

			adj[u].add(v);
			adj[v].add(u);
		}

		int root = 0;

		// 4. root 를 찾는다.
		for (int i = 1; i <= n; i++) {
			if (adj[i].size() > 1) {
				root = i;
				break;
			}
		}

		// 5. val_node 와 visited 배열 초기화 (생략)

		// 6. dfs
		int c = dfs(root);

		for (int i = 1; i <= n; i++) {
			val_node[i-1] = diff(val_node[i], s);
		}

		int [] sub = Arrays.copyOfRange(val_node, 0, n+1);

		Arrays.sort(sub);

		System.out.println(sub[0]);
	}
}

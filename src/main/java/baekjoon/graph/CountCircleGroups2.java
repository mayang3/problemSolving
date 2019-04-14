package baekjoon.graph;

import java.util.Scanner;

/**
 * 두 원의 겹치는 관계를 확인해보자.
 *
 * https://mathbang.net/101
 *
 * 즉, 두 원의 반지름인 r,r' 를 더한 값이 각 원의 중심점의 거리 d 보다 같거나 커야 한다.
 *
 * r+r' >= d 인 점들의 경우 계속해서 더해나가자.
 *
 * 이 문제에서 y,x 2차원 좌표평면인 경우인데도 pos 로 표현하는 부분에 대해 잘 확인해보자.
 *
 * 어쨌든 Union-Find 에서 parent[] 배열은 각각 n 의 수만큼 최초 부모를 가져야 하므로..
 *
 * y,x 좌표평면 데이터를 하나의 인덱스로 표현한다. (여기서 포인트는 이부분이다.)
 *
 * 이 부분만 적용하면 .. dfs 로도 무리 없이 풀리는듯하다..
 *
 * (http://donggod.tistory.com/90 참고)
 *
 * 그리고, 이 인덱스가 parent[] 정수 배열의 인덱스가 된다.!
 *
 */
public class CountCircleGroups2 {

	static Pair [] pos;

	static class Pair {
		int y;
		int x;
		int r;

		Pair(int y, int x, int r) {
			this.y = y;
			this.x = x;
			this.r = r;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();

			pos = new Pair[n];

			for (int i = 0; i < n; i++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				int r = scanner.nextInt();


				pos[i] = new Pair(y, x, r);
			}

			UnionFind unionFind = new UnionFind(n);

			for (int i = 0; i < n; i++) {
				for (int j = i-1; j >= 0; j--) {
					// (주의!) 여기서 만약에 j 가 0 부터 시작한다면 무조건 i 점과 계속해서 merge 가 되므로 오답이 나온다.
					if (isConnect(i, j) && unionFind.parent[i] != unionFind.parent[j]) {
						unionFind.union(i, j);
					}
				}
			}

			int cnt = 0;

			for (int i = 0; i < n; i++) {

				// union - find 에서 자기 자신이 parent 인 노드만 찾는 트릭.
				// 최초에 parent 는 i 로 각각 초기화 되므로, 아무리 union 된다고 해도 i 의 범위 [0...n) 을 벗어날 수 없다.
				// 그러므로, find() 를 수행했을때, i 자기 자신이 나오는 노드는 루트 노드이다.
				if (i == unionFind.find(i)) {
					cnt++;
				}
			}

			System.out.println(cnt);
		}
	}

	// 여기서 i, j 는 하나의 점(Pair class) 이다.
	// pos 배열에서의 i 번째 점, j 번째 점을 나타낸다.
	// 즉, 모든 점을 완전 탐색하므로, 전파 수신범위에서 예외의 경우는 존재하지 않는다.
	// 예를 들어, [0,0,4] , [10,10,1], [1,1,2] 가 차로 입력되었을때,
	// 1번째 점과 3번째 점이 연관관계가 있으므로, 이 두 점이 서로 비교가 되어야 한다.
	static boolean isConnect(int a, int b) {
		Pair p1 = pos[a];
		Pair p2 = pos[b];

		int y = p1.y - p2.y;
		int x = p1.x - p2.x;
		int r = p1.r + p2.r;

		return r * r >= y*y + x*x;
	}

	static class UnionFind {
		int [] rank;
		int [] parent;
		int n;

		UnionFind(int n) {
			this.rank = new int[n];
			this.parent = new int[n];
			this.n = n;

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		int find(int x) {
			if (parent[x] == x) {
				return x;
			}

			return parent[x] = find(parent[x]);
		}

		void union(int x, int y) {
			int xRoot = find(x);
			int yRoot = find(y);

			if (xRoot == yRoot) {
				return;
			}

			// 랭킹(트리의 높이)이 큰 쪽의 트리에 머지시킨다.
			if (rank[xRoot] < rank[yRoot]) {
				parent[xRoot] = yRoot;
			} else if (rank[xRoot] > rank[yRoot]) {
				parent[yRoot] = xRoot;
			} else {
				// 랭킹이 같은 경우 둘중 하나를 나머지 하나의 자식트리로 머지시킨다.
				// 아래와 같은 경우 yRoot 의 부모가 xRoot 로 바뀐것이므로..
				// yRoot 가 xRoot 하위로 머지된 것이다.
				parent[yRoot] = xRoot;
				// 그러므로 xRoot 의 랭킹을 +1 해준다.
				// 즉, 랭킹은 트리의 높이다.
				rank[xRoot] = rank[xRoot] + 1;
			}
		}
	}
}

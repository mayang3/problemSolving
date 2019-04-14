package baekjoon.dp;

import java.util.*;

/**
 * Longest Common Subsequence2
 */
@SuppressWarnings("ALL")
public class LCS2 {
	static char [] A;
	static char [] B;
	static int [][] cache = new int[1001][1001];

	static class Node {
		char ch;
		int cnt;

		Node(char ch, int cnt) {
			this.ch = ch;
			this.cnt = cnt;
		}
	}

	static LinkedList<Node> list = new LinkedList<>();

	/**
	 * 기본 아이디어는 LCS1 에서 cand > cache[i][j] 인 경우에만 list 를 추가한다는 생각이 맞다..
	 *
	 * 하지만, 이때 다른 경우의 수를 탐색한 경우에도 list 에 모두 추가되었다.
	 *
	 * 이럴 경우 어떻게 했냐 하면..
	 *
	 * list 를 다시 복원하면서, MAX 경우의 수에 해당하지 않는 값이라면 복원대상에서 제외시켰다.
	 *
	 * 반대로 MAX 경우의 수에 해당하는 경우만 index 에 저장시켜 놓은다음에 나중에 복원도 가능할듯 하다..
	 * -> 요건 시간될때 한번 해보자.. 해보진 않음
	 *
	 * @param i
	 * @param j
	 * @return
	 */
	static int solve(int i, int j) {
		// A, B 모두 끝까지 도착했다면 종료한다.
		if (i==A.length-1 && j==B.length-1) {
			if (A[i] == B[j]) {
				list.add(new Node(A[i], 1));
				return 1;
			}
			return 0;
		}

		if (cache[i][j] != -1) {
			return cache[i][j];
		}

		cache[i][j] = 0;

		if (j < B.length && i < A.length && A[i] == B[j]) {
			int cand = 1 + solve(i+1, j+1);
			// 아래의 figure1,2 번에서 같은 max 값을 지닌 값들이 캐시될 수 있지만,
			// 이곳에서 추가되는 list 는 [i][j] 당 실제 접근한 하나의 값들만이 고유하게 캐시된다.
			//
			if (cand > cache[i][j]) {
				list.add(new Node(A[i], cand));
				cache[i][j] = cand;
			}
		} else {
			// figure 1.
			if (j < B.length-1) {
				cache[i][j] = Math.max(cache[i][j], solve(i, j + 1));
			}

			// figure 2.
			if (i < A.length-1) {
				cache[i][j] = Math.max(cache[i][j], solve(i + 1, j));
			}
		}

		return cache[i][j];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		A = scanner.next().toCharArray();
		B = scanner.next().toCharArray();

		cache = new int[A.length+1][B.length+1];

		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		int retCount = solve(0,0);
		System.out.println(retCount);

		StringBuilder sb = new StringBuilder();

		for (int i=list.size()-1 ; i>=0 ; i--) {
			Node node = list.get(i);

			if (node.cnt == retCount) {
				sb.append(node.ch);
				retCount--;
			}
		}

		System.out.println(sb.toString());
	}
}

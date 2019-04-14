package hackerrank.cs.algorithm.sorting;

import java.util.*;

/**
 * 내 아이디어.
 *
 * 1. 각 수마다 자기 자신이 위치할 위치를 알고 있다.
 * 2. 원본 배열에서 자신이 위치할 수를 찾아가면서 들어가다 보면, 사이클이 생기게 된다.
 * 3. 이 때 이 사이클의 간선 수 - 1 개가 swap 하는 수가 된다.
 * 4. 단, 이때 절대값이 제일 작은 경우를 찾아야 하므로, 음수 or 양수인 경우가 모두 가능하다.
 * -> 그러므로 정방향과 역방향 모두 탐색해준다.
 *
 *
 */
@SuppressWarnings("ALL")
public class LilysHomework {

	static class Pair {
		int num;
		int index;

		Pair(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];
		// 각 값이 들어갈 위치를 저장하는 배열
		List<Integer> copyList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
			copyList.add(arr[i]);
		}

		// 정방향 위치
		Collections.sort(copyList);
		int forwardSwap = computeSwap(n, arr, copyList);

		// 역방향 위치
		Collections.sort(copyList, Collections.reverseOrder());
		int reverseSwap = computeSwap(n, arr, copyList);

		System.out.println(Math.min(forwardSwap, reverseSwap));
	}

	public static int computeSwap(int n, int[] arr, List<Integer> copyList) {
		Map<Integer, Integer> valueIndexMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			valueIndexMap.put(copyList.get(i), i);
		}

		boolean [] visited = new boolean[n];

		int swap = 0 ;

		// 각 위치를 찾으면서, 사이클이 생기면 사이클 - 1 이 swap 의 갯수가 된다.
		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				swap += (dfs(i, visited, arr, valueIndexMap) - 1);
			}
		}
		return swap;
	}

	static int dfs(int i, boolean[] visited, int[] arr, Map<Integer, Integer> valueIndexMap) {
		if (visited[i]) {
			return 0;
		}

		visited[i] = true;

		return dfs(valueIndexMap.get(arr[i]), visited, arr, valueIndexMap) + 1;
	}
}

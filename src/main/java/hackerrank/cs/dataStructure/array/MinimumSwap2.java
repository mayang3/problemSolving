package hackerrank.cs.dataStructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * {@link geeksforgeeks.array.MinimumSwap}
 *
 */
@SuppressWarnings("Duplicates")
public class MinimumSwap2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		System.out.println(minSwaps(arr));
		
	}

	static class Pair {
		int val;
		int pos;

		Pair(int val, int pos) {
			this.val = val;
			this.pos = pos;
		}

		int getVal() {
			return this.val;
		}

		int getPos() {
			return this.pos;
		}
	}

	static int minSwaps(int [] arr) {
		int n = arr.length;

		// Create two arrays and use as pairs
		// -> Pair 를 이용해 두개의 array 를 동시에 표현한다..
		// where first array is element
		// and second array is position of first element.
		List<Pair> arrPos = new ArrayList<>();

		// 노드를, 정렬되지 않은 Array 의 값과 index 로 표현한다.
		for (int i = 0; i < n; i++) {
			arrPos.add(new Pair(arr[i], i));
		}


		// array 를 값으로 오름차순 정렬한다.
		arrPos.sort((o1, o2) -> {
			if (o1.getVal() > o2.getVal()) {
				return 1;
			} else if (o1.getVal() == o2.getVal()) {
				return 0;
			} else {
				return -1;
			}
		});

		boolean [] visited = new boolean[n];

		Arrays.fill(visited, false);

		int ans = 0;

		// 각 노드마다 순회하면서 사이클을 찾는다.
		for (int i = 0; i < n; i++) {

			// 이미 swap 되어서 올바른 position 에 위치해 있거나, 기존부터 올바른 position 일 경우에는 skip
			if (visited[i] || arrPos.get(i).getPos() == i) {
				continue;
			}

			int cycleNodeSize = 0;
			int j = i;

			// 연결된 position 을 찾아 jump 하면서 연결된 사이클의 개수를 구한다.
			while (!visited[j]) {
				visited[j] = true;

				j = arrPos.get(j).getPos();
				cycleNodeSize++;
			}

			if (cycleNodeSize > 0) {
				ans += (cycleNodeSize - 1);
			}
		}

		return ans;
	}
}

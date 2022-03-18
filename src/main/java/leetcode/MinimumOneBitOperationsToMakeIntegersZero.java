package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumOneBitOperationsToMakeIntegersZero {
	public int minimumOneBitOperations(int n) {
		// BFS
		int cnt = 0;

		Queue<Integer> q = new LinkedList<>();
		q.add(n);

		Set<Integer> visited = new HashSet<>();

		while (!q.isEmpty()) {
			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				int poll = q.poll();

				if (poll == 0) {
					return cnt;
				}

				// 1st op
				int op1 = poll ^ 1;
				if (visited.contains(op1) == false) {
					visited.add(op1);
					q.add(op1);
				}

				// 2st op
				for (int j = 0; j < Integer.toBinaryString(poll).length(); j++) {
					if (isChangeableByOp2(poll, j)) {
						int op2 = poll ^ (1 << (Integer.toBinaryString(poll).length() - (j+1)));

						if (visited.contains(op2) == false) {
							visited.add(op2);
							q.add(op2);
						}
					}
				}
			}

			cnt++;
		}

		return -1;
	}

	boolean isChangeableByOp2(int n, int i) {
		return Integer.numberOfTrailingZeros(n) == Integer.toBinaryString(n).length() - (i+2);
	}

	public static void main(String[] args) {
		MinimumOneBitOperationsToMakeIntegersZero minimumOneBitOperationsToMakeIntegersZero = new MinimumOneBitOperationsToMakeIntegersZero();
		System.out.println(minimumOneBitOperationsToMakeIntegersZero.minimumOneBitOperations((int)1e9));
	}
}

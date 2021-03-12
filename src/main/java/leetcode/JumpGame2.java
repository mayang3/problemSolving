package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame2 {
	public int jump(int[] nums) {
		boolean [] visited = new boolean[nums.length];
		int n = nums.length;

		if (n == 1) {
			return 0;
		}

		Queue<Integer> q = new LinkedList<>();
		q.add(0);

		int step = 0;

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				int curIdx = q.poll();
				int val = nums[curIdx];

				for (int j = curIdx; j <= curIdx+val && j < n; j++) {
					if (j == n-1) {
						return step + 1;
					}

					if (visited[j] == false) {
						visited[j] = true;
						q.add(j);
					}
				}
			}

			step++;

		}

		return -1;
	}

	public static void main(String[] args) {
		int [] nums = {2,3,0,1,4};

		JumpGame2 jumpGame2 = new JumpGame2();
		System.out.println(jumpGame2.jump(nums));
	}
}

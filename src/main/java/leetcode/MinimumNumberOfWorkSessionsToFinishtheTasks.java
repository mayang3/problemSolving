package leetcode;

public class MinimumNumberOfWorkSessionsToFinishtheTasks {
	public int minSessions(int[] tasks, int sessionTime) {
		int left = 1;
		int right = tasks.length;

		// 총 할당 가능한 작업의 수. 최대 task 의 개수만큼 할당이 가능하다.
		while (left < right) {
			int m = (left + right) / 2;

			int[] remain = new int[m];

			// 각 세션 마다 sessionTime 을 가진다.
			for (int i = 0; i < m; i++) {
				remain[i] = sessionTime;
			}

			// 현재 session 의 갯수가 모든 task 를 커버한다면 리턴한다.
			// 작은 값부터 탐색하므로 이 값은 항상 최적이 된다.
			if (canWork(tasks, 0, remain)) {
				right = m;
			} else {
				left = m + 1;
			}
		}

		return left;
	}

	private boolean canWork(int[] tasks, int cur, int[] remain) {
		if (cur == tasks.length) {
			return true;
		}

		// DFS
		for (int i = 0; i < remain.length; i++) {
			// for pruning
			if (i > cur) {
				break;
			}

			// 남아있는 sessionTime 에 현재 작업이 할당 가능한 수준이면..
			if (remain[i] >= tasks[cur]) {
				remain[i] -= tasks[cur];

				if (canWork(tasks, cur + 1, remain)) {
					return true;
				}

				remain[i] += tasks[cur];
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int [] tasks = {3,1,3,1,1};
		int session = 8;

		MinimumNumberOfWorkSessionsToFinishtheTasks minimumNumberOfWorkSessionsToFinishtheTasks = new MinimumNumberOfWorkSessionsToFinishtheTasks();
		System.out.println(minimumNumberOfWorkSessionsToFinishtheTasks.minSessions(tasks, session));
	}
}

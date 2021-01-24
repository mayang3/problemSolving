package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List<Integer> [] adj = new List[numCourses];

		for (int i = 0; i < numCourses; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int [] prerequisite : prerequisites) {
			int v = prerequisite[0];
			int u = prerequisite[1];

			adj[u].add(v);
		}

		boolean [] visited = new boolean[numCourses];

		for (int cur = 0; cur < numCourses; cur++) {
			if (isCycle(adj, visited, cur)) {
				return false;
			}
		}

		return true;
	}

	private boolean isCycle(List<Integer>[] adj, boolean[] visited, int cur) {
		if (visited[cur]) {
			return true;
		}

		visited[cur] = true;

		for (int i = 0; i < adj[cur].size(); i++) {
			int there = adj[cur].get(i);

			if (isCycle(adj, visited, there)) {
				return true;
			}
		}

		// 이 부분이 key point 이다.
		// 각 노드를 시작점으로 하는 부분으로 할때, 그 노드부터 시작해서 연결된 인접 리스트를 모두 탐색하고 나서, 다시 false 로 셋팅해주는 것.
		// 이렇게 해야만 그 다음 노드를 시작할때, 다시 cycle 을 제대로 판별할 수 있다.

		// 만약 이렇게 초기화를 하지 않는다면.. 다음 노드를 탐색할때, cycle 이 아니고 단순히 참조만 하더라도..
		// visited 를 true 로 판단하기 때문에 싸이클이 있다고 보게 된다.
		visited[cur] = false;

		return false;
	}

	public static void main(String[] args) {
		int [][] prerequisites = {{1, 0}};

		CourseSchedule courseSchedule = new CourseSchedule();
		System.out.println(courseSchedule.canFinish(2, prerequisites));
	}
}

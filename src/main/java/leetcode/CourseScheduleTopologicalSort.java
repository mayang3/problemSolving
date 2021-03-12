package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CourseScheduleTopologicalSort {
	public boolean canFinish(int numCourses, int[][] prerequisites) {

		if (prerequisites.length == 0)
			return true; // no cycle could be formed in empty graph.

		// course -> list of next courses
		HashMap<Integer, Node> graph = new HashMap<>();

		// build the graph first
		for (int[] relation : prerequisites) {
			// relation[1] -> relation[0]
			Node prevCourse = this.getCreateGNode(graph, relation[1]);
			Node nextCourse = this.getCreateGNode(graph, relation[0]);

			// dependency 가 있는 노드들간의 설정을 해준다.
			prevCourse.outNodes.add(relation[0]);
			nextCourse.inDegrees += 1;
		}

		// 2. We start from courses that have no prerequisites.
		int totalDeps = prerequisites.length;

		// 2-1. dependency 가 없는 course 를 먼저 찾는다.
		// no dependency courses
		LinkedList<Integer> nodepCourses = new LinkedList<>();

		for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
			Node node = entry.getValue();

			if (node.inDegrees == 0) {
				nodepCourses.add(entry.getKey());
			}
		}

		// 2-2. dependency 가 없는 courses 를 시작점으로 순회하면서 childNode 를 탐색한다.
		int removedEdges = 0;

		while (nodepCourses.size() > 0) {
			Integer course = nodepCourses.pop();

			for (Integer nextCourse : graph.get(course).outNodes) {
				Node childNode = graph.get(nextCourse);
				childNode.inDegrees -= 1;
				removedEdges += 1;
				if (childNode.inDegrees == 0)
					nodepCourses.add(nextCourse);
			}
		}

		// 만약 cycle 이 있다면 cycle 내에 존재하는 간선들은 삭제되지 않는다.
		if (removedEdges != totalDeps)
			// if there are still some edges left, then there exist some cycles
			// Due to the dead-lock (dependencies), we cannot remove the cyclic edges
			return false;
		else
			return true;
	}

	/**
	 * Retrieve the existing <key, value> from graph, otherwise create a new one.
	 */
	protected Node getCreateGNode(HashMap<Integer, Node> graph, Integer course) {
		Node node = null;
		if (graph.containsKey(course)) {
			node = graph.get(course);
		} else {
			node = new Node();
			graph.put(course, node);
		}
		return node;
	}
}

class Node {
	public Integer inDegrees = 0;
	public List<Integer> outNodes = new LinkedList<Integer>();
}

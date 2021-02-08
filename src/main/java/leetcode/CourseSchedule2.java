package leetcode;

import java.util.*;

public class CourseSchedule2 {
	public int[] findOrder(int numCourses, int[][] prerequisites) {

		Map<Integer, Node> graph = new HashMap<>();

		for (int i = 0; i < numCourses; i++) {
			graph.put(i, new Node(i));
		}

		for (int [] prerequisite : prerequisites) {
			Node previous = graph.computeIfAbsent(prerequisite[1], t -> new Node(prerequisite[1]));
			Node next = graph.computeIfAbsent(prerequisite[0], t -> new Node(prerequisite[0]));

			next.inDegrees += 1;
			previous.outNodes.add(next);
		}

		LinkedList<Node> noInDegreeList = new LinkedList<>();

		for (Node node : graph.values()) {
			if (node.inDegrees == 0) {
				noInDegreeList.add(node);
			}
		}

		List<Integer> res = new ArrayList<>();

		while (noInDegreeList.size() > 0) {
			Node node = noInDegreeList.removeFirst();
			res.add(node.val);

			for (Node child : node.outNodes) {
				child.inDegrees -= 1;

				if(child.inDegrees == 0) {
					noInDegreeList.add(child);
				}
			}
		}

		if (res.size() != numCourses) {
			return new int[0];
		}

		return res.stream().mapToInt(i -> i).toArray();
	}

	static class Node {
		int inDegrees;
		int val;
		List<Node> outNodes = new ArrayList<>();

		public Node(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		int numCourses = 1;
		int [][] prerequisites = {};

		CourseSchedule2 courseSchedule2 = new CourseSchedule2();
		System.out.println(Arrays.toString(courseSchedule2.findOrder(numCourses, prerequisites)));
	}

}

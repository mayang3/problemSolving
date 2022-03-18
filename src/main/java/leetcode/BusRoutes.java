package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class BusRoutes {
	public int numBusesToDestination(int[][] routes, int source, int target) {

		if (source == target) {
			return 0;
		}
		Map<Integer, Set<Integer>> busStops = new HashMap<>();
		Map<Integer, Set<Integer>> busGraph = new HashMap<>();

		for (int i = 0; i < routes.length; i++) {
			Set<Integer> busStop = Arrays.stream(routes[i]).mapToObj(value -> value).collect(Collectors.toSet());
			busStops.computeIfAbsent(i, t -> new HashSet<>()).addAll(busStop);
		}

		for (int i = 0; i < routes.length; i++) {
			for (int j = i+1; j < routes.length; j++) {
				Set<Integer> setA = busStops.get(i);
				Set<Integer> setB = busStops.get(j);

				if (intersect(setA, setB)) {
					busGraph.computeIfAbsent(i, t -> new HashSet<>()).add(j);
					busGraph.computeIfAbsent(j, t -> new HashSet<>()).add(i);
				}
			}

		}


		boolean [] visited = new boolean[routes.length];

		Set<Integer> targetBusSet = new HashSet<>();
		Queue<Integer> q = new LinkedList<>();

		for (Map.Entry<Integer, Set<Integer>> busStopSet : busStops.entrySet()) {
			if (busStopSet.getValue().contains(source)) {
				q.add(busStopSet.getKey());
			}

			if (busStopSet.getValue().contains(target)) {
				targetBusSet.add(busStopSet.getKey());
			}
		}

		int taken = 1;

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				int here = q.poll();

				if (targetBusSet.contains(here)) {
					return taken;
				}

				if (busGraph.containsKey(here)) {
					for (int there : busGraph.get(here)) {
						if (visited[there] == false) {
							visited[there] = true;
							q.add(there);
						}
					}
				}
			}

			taken++;
		}

		return -1;
	}

	private boolean intersect(Set<Integer> A, Set<Integer> B) {
		for (int a : A) {
			if (B.contains(a)) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int [][] routes = {{0,1,6,16,22,23},{14,15,24,32},{4,10,12,20,24,28,33},{1,10,11,19,27,33},{11,23,25,28},{15,20,21,23,29},{29}};
		int source = 4;
		int target = 21;

		BusRoutes busRoutes = new BusRoutes();
		System.out.println(busRoutes.numBusesToDestination(routes, source, target));
	}
}

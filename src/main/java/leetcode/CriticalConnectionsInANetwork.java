package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriticalConnectionsInANetwork {

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<List<Integer>> ret = new ArrayList<>();

		Map<Integer, List<Integer>> graph = new HashMap<>();

		for (List<Integer> connection : connections) {
			put(graph,connection.get(0),connection.get(1));
			put(graph,connection.get(1),connection.get(0));
		}

		Map<Integer, Integer> m1 = new HashMap<>();
		Map<Integer, Integer> m2 = new HashMap<>();

		for (Map.Entry<Integer, List<Integer>> entry: graph.entrySet()) {
			int key = entry.getKey();

			if (entry.getValue().size() == 1) {
				if (!m1.containsKey(key) && !m2.containsKey(key)) {
					m1.put(key, entry.getValue().get(0));
					m2.put(entry.getValue().get(0), key);
				}
			}
		}

		for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
			List<Integer> inner = new ArrayList<>();

			inner.add(entry.getKey());
			inner.add(entry.getValue());

			ret.add(inner);
		}

		return ret;
	}

	static void put(Map<Integer, List<Integer>> graph, int u, int v) {
		if (graph.containsKey(u)) {
			graph.get(u).add(v);
		} else {
			List<Integer> newList = new ArrayList<>();

			newList.add(v);

			graph.put(u, newList);
		}
	}

	public static void main(String[] args) {

		List<List<Integer>> connections = new ArrayList<>();

		List<Integer> i1 = new ArrayList<>();
		i1.add(0);
		i1.add(1);

		List<Integer> i2 = new ArrayList<>();
		i2.add(1);
		i2.add(2);

		List<Integer> i3 = new ArrayList<>();
		i3.add(2);
		i3.add(0);

		List<Integer> i4 = new ArrayList<>();
		i4.add(1);
		i4.add(3);

		connections.add(i1);
		connections.add(i2);
		connections.add(i3);
		connections.add(i4);

		CriticalConnectionsInANetwork criticalConnectionsInANetwork = new CriticalConnectionsInANetwork();
		List<List<Integer>> lists = criticalConnectionsInANetwork.criticalConnections(4, connections);

		System.out.println(lists);
	}
}

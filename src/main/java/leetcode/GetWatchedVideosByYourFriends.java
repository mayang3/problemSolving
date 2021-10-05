package leetcode;

import java.util.*;

public class GetWatchedVideosByYourFriends {

	public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();

		for (int i = 0; i < friends.length; i++) {
			for (int friend : friends[i]) {
				graph.computeIfAbsent(i, t -> new HashSet<>()).add(friend);
			}
		}

		Set<Integer> visited = new HashSet<>();

		Queue<Integer> q = new LinkedList<>();
		q.add(id);
		visited.add(id);

		int cnt = 0;

		Map<String, Integer> countMap = new HashMap<>();

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				int here = q.poll();

				if (cnt == level) {
					for (String s : watchedVideos.get(here)) {
						countMap.merge(s, 1, Integer::sum);
					}

					continue;
				}

				if (graph.containsKey(here)) {
					for (int there : graph.get(here)) {
						if (visited.contains(there) == false) {
							q.add(there);
							visited.add(there);
						}
					}
				}
			}

			cnt++;
		}

		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.getValue() - o2.getValue() == 0) {
				return o1.getKey().compareTo(o2.getKey());
			}

			return o1.getValue() - o2.getValue();
		});

		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			pq.add(entry);
		}

		List<String> res = new ArrayList<>();

		while (pq.isEmpty() == false) {
			res.add(pq.poll().getKey());
		}

		return res;
	}


	public static void main(String[] args) {
		List<List<String>> watchedVideos = new ArrayList<>();

		watchedVideos.add(Arrays.asList(new String[] {"A", "B"}));
		watchedVideos.add(Arrays.asList(new String[] {"C"}));
		watchedVideos.add(Arrays.asList(new String[] {"B", "C"}));
		watchedVideos.add(Arrays.asList(new String[] {"D"}));

		int [][] friends = {{1,2},{0,3},{0,3},{1,2}};
		int id = 0;
		int level = 2;

		GetWatchedVideosByYourFriends getWatchedVideosByYourFriends = new GetWatchedVideosByYourFriends();
		System.out.println(getWatchedVideosByYourFriends.watchedVideosByFriends(watchedVideos, friends, id, level));
	}
}

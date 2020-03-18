package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author neo82
 */
public class TimeBasedKeyValueStore {
	private final Map<String, TreeSet<Pair>> map;

	static class Pair {
		String value;
		int timestamp;

		public Pair(String value, int timestamp) {
			this.value = value;
			this.timestamp = timestamp;
		}
	}

	public TimeBasedKeyValueStore() {
		this.map = new HashMap<>();
	}

	public void set(String key, String value, int timestamp) {
		TreeSet<Pair> treeSet = map.computeIfAbsent(key, (t) -> new TreeSet<>(Comparator.comparingInt(o -> o.timestamp)));
		treeSet.add(new Pair(value, timestamp));
	}

	public String get(String key, int timestamp) {
		TreeSet<Pair> treeSet = map.get(key);

		if (treeSet == null || treeSet.isEmpty()) {
			return "";
		}

		Pair found = treeSet.floor(new Pair(null, timestamp));

		if (found == null) {
			return "";
		}

		return found.value;
	}

	public static void main(String[] args) {
		TimeBasedKeyValueStore timeBasedKeyValueStore = new TimeBasedKeyValueStore();

		timeBasedKeyValueStore.set("love","high",10);
		timeBasedKeyValueStore.set("love","low",20);

		System.out.println(timeBasedKeyValueStore.get("love",5));
		System.out.println(timeBasedKeyValueStore.get("love",10));
		System.out.println(timeBasedKeyValueStore.get("love",15));
		System.out.println(timeBasedKeyValueStore.get("love",20));
		System.out.println(timeBasedKeyValueStore.get("love",25));

	}
}

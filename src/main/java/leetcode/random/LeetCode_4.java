package leetcode.random;

import java.util.LinkedHashMap;

public class LeetCode_4 {

	static class LRUCache {
		private final LinkedHashMap<Integer, Integer> map;
		private final int capacity;

		public LRUCache(int capacity) {
			this.capacity = capacity;
			this.map = new LinkedHashMap<>(capacity);
		}

		public int get(int key) {
			Integer v = map.remove(key);

			if (v == null) {
				return -1;
			}
			map.put(key, v);

			return v;
		}

		public void put(int key, int value) {
			if (this.capacity == 0) {
				return;
			}

			// LRU 에 이미 해당 key 가 있는 경우 -> 해당 key 를 지우고 다시 추가
			if (map.containsKey(key)) {
				map.remove(key);
			} else if (this.capacity == map.size()){
				// 새로운 key 가 들어왔는데 용량이 없는 경우 -> 가장 오래전에 남아있는 녀석을 지우고 새로운 녀석을 추가
				map.remove(map.keySet().iterator().next());
			}

			// 용량이 넉넉하고 새로운 녀석이 들어온 경우. 새로운 녀석만 추가
			map.put(key, value);
		}
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);

		cache.put(2, 1);
		cache.put(3, 2);

		System.out.println(cache.get(3));
		System.out.println(cache.get(2));

		cache.put(4, 3);

		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));

	}
}

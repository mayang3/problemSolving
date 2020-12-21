package leetcode;

import java.util.*;

public class RandomizedCollection {
	List<Integer> list;
	Map<Integer, Set<Integer>> map;
	Random random;

	/** Initialize your data structure here. */
	public RandomizedCollection() {
		this.list = new ArrayList<>();
		this.map = new HashMap<>();
		this.random = new Random();
	}

	/** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
	public boolean insert(int val) {
		list.add(val);

		boolean contains = map.containsKey(val);

		map.computeIfAbsent(val, t -> new HashSet<>()).add(list.size()-1);

		return contains == false;
	}

	/** Removes a value from the collection. Returns true if the collection contained the specified element. */
	public boolean remove(int val) {
		if (map.getOrDefault(val, new HashSet<>()).size() == 0) {
			return false;
		}

		int lastIndex = list.size() - 1;
		int targetIndex = map.get(val).iterator().next();

		int lastVal = list.get(lastIndex);
		int targetVal = list.get(targetIndex);


		// or !map.get(val).contains(list.size() -1) 도 가능하다.
		// val 이 가지고 있는 index 에 마지막 index 가 포함되어 있는지를 확인해야 한다.
		if (targetIndex < lastIndex && lastVal != targetVal) {
			// 맨 마지막 index 의 값을 targetIndex 위치에 넣는다.
			list.set(targetIndex, lastVal);

			// map 에서 맨 마지막 value 의 index 의 값을 갱신한다.
			map.get(lastVal).remove(lastIndex);
			map.get(lastVal).add(targetIndex);

			// map 에서 타겟 value 의 index 값을 갱신한다.
			map.get(targetVal).remove(targetIndex);
			map.get(targetVal).add(lastIndex);
		}

		list.remove(lastIndex);
		map.get(val).remove(lastIndex);
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return list.get(random.nextInt(list.size()));
	}

	public static void main(String[] args) {
		RandomizedCollection collection = new RandomizedCollection();

		System.out.println(collection.insert(4));
		System.out.println(collection.insert(3));
		System.out.println(collection.insert(4));
		System.out.println(collection.insert(2));
		System.out.println(collection.insert(4));

		System.out.println(collection.remove(4));
		System.out.println(collection.remove(3));
		System.out.println(collection.remove(4));
		System.out.println(collection.remove(4));
	}
}

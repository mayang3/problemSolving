package leetcode;

import java.util.*;

public class InsertDeleteGetRandomO1 {
	static class RandomizedSet {

		List<Integer> dataList;
		Map<Integer, Integer> indexMap;

		/** Initialize your data structure here. */
		public RandomizedSet() {
			this.dataList = new ArrayList<>();
			this.indexMap = new HashMap<>();
		}

		/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
		public boolean insert(int val) {
			if (indexMap.containsKey(val)) {
				return false;
			}

			this.indexMap.put(val,dataList.size());
			this.dataList.add(val);

			return true;
		}

		/** Removes a value from the set. Returns true if the set contained the specified element. */
		public boolean remove(int val) {
			if (indexMap.containsKey(val) == false) {
				return false;
			}

			int removeIndex = indexMap.get(val);
			int lastIndex = this.dataList.size() - 1;


			// 지워야 할 대상이 가장 끝 값보다 작을 경우, 맨 뒤로 항상 가게 해준다.
			// 이렇게 하면, 나머지 인덱스를 모두 당길 필요가 없다.
			if (removeIndex < lastIndex) {
				int lastItem = dataList.get(lastIndex);
				dataList.set(removeIndex, lastItem);
				indexMap.put(lastItem, removeIndex);
			}

			this.dataList.remove(lastIndex);
			this.indexMap.remove(val);

			return true;
		}

		/** Get a random element from the set. */
		public int getRandom() {
			int index = (int)(Math.random() * this.dataList.size());

			return this.dataList.get(index);
		}
	}

	public static void main(String[] args) {
		RandomizedSet randomizedSet = new RandomizedSet();

		System.out.println(randomizedSet.insert(0));
		System.out.println(randomizedSet.insert(1));
		System.out.println(randomizedSet.remove(0));
		System.out.println(randomizedSet.insert(2));
		System.out.println(randomizedSet.remove(1));
		System.out.println(randomizedSet.getRandom());
	}
}

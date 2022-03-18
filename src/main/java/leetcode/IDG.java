package leetcode;

import java.util.*;

public class IDG {
    static class RandomizedSet {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Random r = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }

            list.add(val);
            map.put(val, list.size()-1);

            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (map.containsKey(val) == false) {
                return false;
            }

            int idx = map.get(val);
            int lastIdx = list.size()-1;

            if (idx < lastIdx) {
                int lastVal =  list.get(lastIdx);
                list.set(idx, lastVal);
                map.put(lastVal, idx);
            }

            list.remove(idx);
            map.remove(val);

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(r.nextInt(list.size()));
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        boolean insert = randomizedSet.insert(1);// Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println(insert);
        boolean remove = randomizedSet.remove(2);// Returns false as 2 does not exist in the set.
        System.out.println(remove);
        boolean insert1 = randomizedSet.insert(2);// Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println(insert1);
        int random = randomizedSet.getRandom();// getRandom() should return either 1 or 2 randomly.
        System.out.println(random);
        boolean remove1 = randomizedSet.remove(1);// Removes 1 from the set, returns true. Set now contains [2].
        System.out.println(remove1);
        boolean insert2 = randomizedSet.insert(2);// 2 was already in the set, so return false.
        System.out.println(insert2);
        int random1 = randomizedSet.getRandom();// Since 2 is the only number in the set, getRandom() will always return 2.
        System.out.println(random1);
    }
}

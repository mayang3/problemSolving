package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FindSumPairs {

    Map<Integer, Integer> map1 = new HashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();
    int [] nums2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            map1.merge(nums1[i], 1, Integer::sum);
        }

        for (int i = 0; i < nums2.length; i++) {
            map2.merge(nums2[i], 1, Integer::sum);
        }

        this.nums2 = nums2;
    }

    public void add(int index, int val) {
        int beforeVal = this.nums2[index];
        int afterVal = beforeVal + val;

        map2.merge(beforeVal, -1, Integer::sum);

        if (map2.get(beforeVal) == 0) {
            map2.remove(beforeVal);
        }

        map2.merge(afterVal, 1, Integer::sum);
        this.nums2[index] = afterVal;
    }

    public int count(int tot) {
        int count = 0;

        for (int num1 : map1.keySet()) {
            if (map2.containsKey(tot - num1)) {
                count += (map1.get(num1) * map2.get(tot - num1));
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int [] nums1 = {1, 1, 2, 2, 2, 3};
        int [] nums2 = {1, 4, 5, 2, 5, 4};

        FindSumPairs findSumPairs = new FindSumPairs(nums1, nums2);
        System.out.println(findSumPairs.count(7));  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and pairs (5,1), (5,5) make 3 + 4
        findSumPairs.add(3, 2); // now nums2 = [1,4,5,4,5,4]
        System.out.println(findSumPairs.count(8));  // return 2; pairs (5,2), (5,4) make 3 + 5
        System.out.println(findSumPairs.count(4));  // return 1; pair (5,0) makes 3 + 1
        findSumPairs.add(0, 1); // now nums2 = [2,4,5,4,5,4]
        findSumPairs.add(1, 1); // now nums2 = [2,5,5,4,5,4]
        System.out.println(findSumPairs.count(7));  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make 2 + 5 and pairs (5,3), (5,5) make 3 + 4
    }
}

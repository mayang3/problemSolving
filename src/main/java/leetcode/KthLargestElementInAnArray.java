package leetcode;

import java.util.PriorityQueue;

/**
 * @author neo82
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        int [] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;

        KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();
        System.out.println(kthLargestElementInAnArray.findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int n : nums) {
            priorityQueue.add(n);

            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        return priorityQueue.poll();
    }
}

package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = Arrays.binarySearch(arr, x);

        if (index < 0) {
            index = (-index)-1;
        }

        int left = index -1;
        int right = index;

        LinkedList<Integer> res = new LinkedList<>();

        while (res.size() < k) {
            if (left < 0) {
                res.addLast(arr[right]);
                right++;
            } else if (right >= arr.length) {
                res.addFirst(arr[left]);
                left--;
            } else if (Math.abs(arr[left] - x) < Math.abs(arr[right] - x) || (Math.abs(arr[left] - x) == Math.abs(arr[right] - x) && arr[left] < arr[right])) {
                res.addFirst(arr[left]);
                left--;
            } else {
                res.addLast(arr[right]);
                right++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int [] arr = {1,2,3,6,8};
        int k = 3;
        int x = 3;

        FindKClosestElements findKClosestElements = new FindKClosestElements();
        System.out.println(findKClosestElements.findClosestElements(arr, k, x));
    }
}

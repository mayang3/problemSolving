package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;

        int [] originalSum = new int[n];
        int [] prefixSum = new int[n];
        originalSum[0] = 0;
        prefixSum[0] = arr[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i-1] + arr[i];
            originalSum[i] = originalSum[i-1] + i;
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (originalSum[i] == prefixSum[i]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int [] arr = {1,0,2,3,4};

        MaxChunksToMakeSorted maxChunksToMakeSorted = new MaxChunksToMakeSorted();
        System.out.println(maxChunksToMakeSorted.maxChunksToSorted(arr));
    }
}

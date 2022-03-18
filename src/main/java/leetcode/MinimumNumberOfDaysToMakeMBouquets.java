package leetcode;

import java.util.Arrays;

public class MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        int left = 1;
        int right = (int)1e9;

        // 왜 이퀄 조건이 들어가야 하는가?
        // 의문점들
        // 1. while 문에 이퀄 조건이 빠지는 경우, right 에 middle - 1 이 빠져야 한다. 왜 그런가?
        // 2. left 는 왜 항상 +1 을 해주어야 하는가? 그냥 middle 이면 왜 안되는가?
        while (left <= right) {
            int middle = (left + right) / 2;

            int bouquets = getMaxBouquets(bloomDay, middle, k);

            if (bouquets == 0 && left == (int)1e9) {
                return -1;
            }

            // 부케가 필요한 양보다 더 적게 만들어 졌다면..
            if (bouquets < m) {
                left = middle+1;
            } else {
                right = middle-1;
            }
        }

        return left;
    }

    private int getMaxBouquets(int[] bloomDay, int middle, int k) {
        int window = 0;
        int bouquets = 0;

        for (int i = 0; i < k; i++) {
            if (bloomDay[i] <= middle) {
                window++;
            }
        }

        if (window == k) {
            bouquets++;
        }

        for (int right = k; right < bloomDay.length; right++) {
            int left = right - k;

            if (bloomDay[left] <= middle) {
                window--;
            }

            if (bloomDay[right] <= middle) {
                window++;
            }

            if (window == k) {
                bouquets++;
            }
        }

        return bouquets;
    }

    static void bs(int [] num, int left, int right, int target) {
        if (left > right) {
            return;
        }

        int mid = (left + right) / 2;

        if (num[mid] == target) {
            System.out.println(num[mid]);
            return;
        }

        if (num[mid] > target) {
            bs(num , left, mid, target);
        } else if (num[mid] < target) {
            bs(num, mid+1, right, target);
        }
    }

    public static void main(String[] args) {
        int [] bloomDays = {1,10,3,10,2};
        int m = 3;
        int k = 2;

        int [] num = {1,2,3,4,5,6,7,8,9,10};

//        bs(num, 0, num.length-1, 10);
        MinimumNumberOfDaysToMakeMBouquets minimumNumberOfDaysToMakeMBouquets = new MinimumNumberOfDaysToMakeMBouquets();
        System.out.println(minimumNumberOfDaysToMakeMBouquets.minDays(bloomDays, m, k));
    }

}

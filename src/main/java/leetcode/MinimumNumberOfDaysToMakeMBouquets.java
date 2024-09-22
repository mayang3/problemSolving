package leetcode;

public class MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }

        int left = 1;
        int right = (int) 1e9;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossible(bloomDay, mid, m, k)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isPossible(int[] bloomDay, int targetDay, int m, int k) {
        int count = 0;

        int i = 0;

        while (i < bloomDay.length - k + 1) {
            boolean possible = true;

            int j = i;

            while (j < i + k) {
                if (bloomDay[j++] > targetDay) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                count++;
            }

            i = j;
        }

        return count >= m;
    }


    public static void main(String[] args) {
        int[] bloomDays = {5, 37, 55, 92, 22, 52, 31, 62, 99, 64, 92, 53, 34, 84, 93, 50, 28};
        int m = 8;
        int k = 2;

        MinimumNumberOfDaysToMakeMBouquets minimumNumberOfDaysToMakeMBouquets = new MinimumNumberOfDaysToMakeMBouquets();
        System.out.println(minimumNumberOfDaysToMakeMBouquets.minDays(bloomDays, m, k));
    }

}

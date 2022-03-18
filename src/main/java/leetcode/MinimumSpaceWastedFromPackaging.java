package leetcode;

import java.util.Arrays;

public class MinimumSpaceWastedFromPackaging {
    static int MOD = (int)1e9 + 7;

    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);

        int [] prefixSum = new int[packages.length];
        prefixSum[0] = packages[0];

        for (int i = 1; i < packages.length; i++) {
            prefixSum[i] = prefixSum[i-1] + packages[i];
        }

        int min = Integer.MAX_VALUE;

        for (int [] boxesBySupplier : boxes) {
            Arrays.sort(boxesBySupplier);

            // 전부 담을 수 있는 박스가 없는 경우
            if (packages[packages.length-1] > boxesBySupplier[boxesBySupplier.length-1]) {
                continue;
            }

            int loss = 0;
            int i = 0;

            for (int box : boxesBySupplier) {
                int j = Arrays.binarySearch(packages, box);

                if (j == -1) {
                    continue;
                }

                if (j < 0) {
                    j = -(j+2);
                }

                loss = (loss + ((j-i+1) * box) % MOD - (prefixSum[j] - (i == 0 ? 0 : prefixSum[i-1]))) % MOD;
                i = j+1;
            }

            min = Math.min(min, loss);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        int [] packages = {7,6,5,3,4};
        int [][] boxes = {{2,7},{6},{10,5}};

        MinimumSpaceWastedFromPackaging minimumSpaceWastedFromPackaging = new MinimumSpaceWastedFromPackaging();
        System.out.println(minimumSpaceWastedFromPackaging.minWastedSpace(packages, boxes));
    }
}

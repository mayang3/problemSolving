package leetcode;

public class NumberOfSubArrayWithOddSum {
    static int MOD = (int)1e9 + 7;

    public int numOfSubarrays(int[] arr) {
        int odd = 0;
        int even = 0;
        int sum = 0;

        for (int n : arr) {
            if (n % 2 == 0) {
                even++;
            } else {
                int temp = odd;
                odd = even + 1;
                even = temp;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int [] arr = {7};

        NumberOfSubArrayWithOddSum numberOfSubArrayWithOddSum = new NumberOfSubArrayWithOddSum();
        System.out.println(numberOfSubArrayWithOddSum.numOfSubarrays(arr));
    }
}

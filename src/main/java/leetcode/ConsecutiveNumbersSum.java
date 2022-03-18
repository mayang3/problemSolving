package leetcode;

public class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int N) {
        int sum = 0, ans = 0;
        for(int i = 1; sum < N; i++) {
            sum += i;
            if (((N-sum) % i) == 0)
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        ConsecutiveNumbersSum consecutiveNumbersSum = new ConsecutiveNumbersSum();
        System.out.println(consecutiveNumbersSum.consecutiveNumbersSum(15));
    }
}

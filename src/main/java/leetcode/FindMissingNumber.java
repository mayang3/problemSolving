package leetcode;

public class FindMissingNumber {
    public int findMissingNumber(int [] A, int n) {
        int X = 0;

        for (int i = 0; i < A.length; i++) {
            X ^= A[i];
        }

        for (int i = 1; i <= n; i++) {
            X ^= i;
        }

        return X;
    }

    public static void main(String[] args) {
        int [] A = new int[100];

        for (int i = 0; i < 100; i++) {
            if (i == 55) {
                A[i] = 0;
            } else {
                A[i] = i + 1;
            }
        }

        FindMissingNumber findMissingNumber = new FindMissingNumber();
        System.out.println(findMissingNumber.findMissingNumber(A, 100));
    }
}

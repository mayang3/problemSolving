package algospot;

import java.util.Scanner;

public class 울타리잘라내기 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while (n-- > 0) {
            int [] arr = new int[scanner.nextInt()];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }

            System.out.println(solve(arr, 0, arr.length-1));
        }
    }

    private static int solve(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int max = 0;

        int m = (left + right) / 2;

        max = solve(arr, left, m);
        max = Math.max(max, solve(arr, m+1, right));

        int curLeft = m;
        int curRight = m;
        int maxHeight = arr[m];

        while (left < curLeft || curRight < right) {
            if (curRight < right && (left == curLeft || arr[curRight+1] >= arr[curLeft-1])) {
                curRight++;
                maxHeight = Math.min(maxHeight, arr[curRight]);
            } else if (left < curLeft) {
                curLeft--;
                maxHeight = Math.min(maxHeight, arr[curLeft]);
            }

            max = Math.max(max, maxHeight * (curRight - curLeft+1));
        }

        return max;
    }
}

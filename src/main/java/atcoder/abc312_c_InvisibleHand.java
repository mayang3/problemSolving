package atcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc312_c_InvisibleHand {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] seller = new int[N];
        int[] buyer = new int[M];

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            seller[i] = sc.nextInt();
            left = Math.min(left, seller[i]);
            right = Math.max(right, seller[i]);
        }

        for (int i = 0; i < M; i++) {
            buyer[i] = sc.nextInt();
            left = Math.min(left, buyer[i]);
            right = Math.max(right, buyer[i]);
        }

        Arrays.sort(seller);
        Arrays.sort(buyer);

        if (seller[0] > buyer[M - 1]) {
            left = buyer[M - 1] + 1;
            right = seller[0];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isSellerGraterOrEqualThanBuyer(seller, buyer, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    private static boolean isSellerGraterOrEqualThanBuyer(int[] seller, int[] buyer, int yen) {
        int sellerCount = getSellerCount(seller, yen);
        int buyerCount = getBuyerCount(buyer, yen);

        return sellerCount >= buyerCount;
    }

    private static int getSellerCount(int[] seller, int yen) {
        int left = 0;
        int right = seller.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (seller[mid] <= yen) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (right < 0 || right > seller.length - 1) {
            return 0;
        }

        return right + 1;
    }

    private static int getBuyerCount(int[] buyer, int yen) {
        int left = 0;
        int right = buyer.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (buyer[mid] >= yen) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left < 0 || left > buyer.length - 1) {
            return 0;
        }

        return buyer.length - left;
    }
}

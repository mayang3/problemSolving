package leetcode;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = (int)1e9;

        while (left <= right) {
            int m = (left + right) / 2;

            if (calCount(piles, h, m)) {
                right = m-1;
            } else {
                left = m+1;
            }
        }

        return left;
    }

    private boolean calCount(int[] piles, int h, int m) {
        int sum = 0;

        for (int i = 0; i < piles.length; i++) {
            sum += (int)Math.ceil((double) piles[i] / (double)m);
        }

         return sum <= h;
    }


    public static void main(String[] args) {
        int [] piles = {1000000000,1000000000};
        int h = 3;

        KokoEatingBananas kokoEatingBananas = new KokoEatingBananas();
        System.out.println(kokoEatingBananas.minEatingSpeed(piles, h));
    }
}

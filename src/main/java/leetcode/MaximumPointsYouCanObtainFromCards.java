package leetcode;

public class MaximumPointsYouCanObtainFromCards {
	public int maxScore(int[] cardPoints, int k) {
		int n = cardPoints.length;
		int totalSum = 0;
		int sum = 0;
		int min = Integer.MAX_VALUE;
		int windowSize = n - k;

		for (int i = 0; i < cardPoints.length; i++) {
			totalSum += cardPoints[i];
		}

		for (int i = 0; i < windowSize; i++) {
			sum += cardPoints[i];
		}

		min = Math.min(min, sum);

		if (windowSize > 0) {
			for (int right = windowSize; right < cardPoints.length; right++) {
				int left = right - windowSize + 1;

				sum -= cardPoints[left-1];
				sum += cardPoints[right];

				min = Math.min(min, sum);
			}
		}

		return totalSum - min;
	}

	public static void main(String[] args) {
		int [] cardPoints = {1,2,3,4,5,6,1};
		int k = 3;

		MaximumPointsYouCanObtainFromCards cards = new MaximumPointsYouCanObtainFromCards();
		System.out.println(cards.maxScore(cardPoints, k));
	}
}

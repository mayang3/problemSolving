package strategies;

/**
 * @author baejunbeom
 */
public class Fence {

	/**
	 * 최초 brute force 방법.
	 *
	 * 답은 나오지만, O(N^2) 이므로 입력값 최대 크기 20,000 에는 무리이다.
	 * @param fences
	 * @return
	 */
	public int bruteForce(int [] fences) {

		int ret = 0;

		for (int i=0 ; i<fences.length ; i++) {
			int minHeight = fences[i];
			for (int j=i ; j<fences.length ; j++) {
				minHeight = Math.min(minHeight, fences[j]);

				int area = (j-i+1) * minHeight;

				ret = Math.max(area, ret);
			}
		}

		return ret;
	}

	public int divideAndConquer(int [] fences, int left, int right) {
		// base case : 판자가 한개인 경우..
		if (left == right) {
			return fences[left];
		}

		int half = (left + right) / 2;

		int ret = Math.max(divideAndConquer(fences, left, half), divideAndConquer(fences, half + 1, right));

		// 부분 문제 3 : 두 부분에 모두 걸치는 사각형 중 가장 큰 것을 찾는다.
		int lo = half, hi = half + 1;

		int height = Math.min(fences[lo], fences[hi]);

		// [mid, mid+1] 만 포함하는 너비 2인 사각형을 고려한다.
		ret = Math.max(ret, height * 2);

		// 사각형이 입력 전체를 덮을 때까지 확장해 나간다.
		while (left < lo || hi < right) {
			// 항상 높이가 더 높은 쪽으로 확장한다.

			// 기준은 오른쪽으로 확장..
			if (hi < right && (lo == left || fences[lo - 1] < fences[hi + 1])) {
				hi++;
				height = Math.min(height, fences[hi]);
			} else {
				lo--;
				height = Math.min(height, fences[lo]);
			}

			// 결국 사각형 전체를 확장해 나가면서 넓이를 모두 구해서 비교해 본다.
			ret = Math.max(ret, height * (hi - lo + 1));
		}

		return ret;
	}


	static final int[] sample1 = {7, 1, 5, 9, 6, 7, 3};
	static final int[] sample2 = {1, 4, 4, 4, 4, 1, 1};

	public static void main(String[] args) {
		Fence fence = new Fence();
		System.out.println(fence.bruteForce(sample1));
		System.out.println(fence.divideAndConquer(sample2, 0, sample2.length - 1));
	}
}

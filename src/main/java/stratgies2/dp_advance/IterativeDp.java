package stratgies2.dp_advance;

public class IterativeDp {

	static int n;
	static int [][] triangle = new int[100][100];
	static int [][] C = new int[100][100];

	static int iterative() {
		// 기저 사례를 계산한다.
		// 여기서 기저 사례는 삼각형의 맨 아랫줄먼저 계산.
		for (int x=0 ; x<n ; x++) {
			C[n-1][x] = triangle[n-1][x];
		}

		// 다른 부분을 계산한다.
		for (int y=n-2 ; y>=0 ; y--) {
			for (int x=0 ; x<y+1 ; x++) {
				C[y][x] = Math.max(C[y+1][x], C[y+1][x+1]) + triangle[y][x];
			}
		}

		return C[0][0];
	}

	static int [][] C2 = new int[2][10000];

	/**
	 *
	 * @return
	 */
	static int slidingWindow() {
		// base case
		for (int x=0 ; x<n ; x++) {
			C2[(n-1)%2][x] = triangle[n-1][x];
		}

		// 다른 부분을 계산한다.
		for (int y=n-2; y>=0 ; y--) {
			for (int x=0 ; x<y+1 ; x++) {
				C2[y%2][x] = Math.max(C2[(y+1)%2][x], C2[(y+1)%2][x+1]) + triangle[y][x];
			}
		}

		return C2[0][0];
	}
}

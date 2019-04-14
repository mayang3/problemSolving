package strategies;

public class TrianglePath {

	int n = 5;
	int triangle[][] = {
		{1},
		{2,4},
		{8,16,8},
		{32,64,32,64},
		{128,256,128,256,128}
	};
	int [][][] cache;

	public int path1(int x, int y, int sum) {
		// base case : 맨 아래줄 도달
		if (x == n - 1) {
			return sum + triangle[x][y];
		}

		// 메모이제이션
		if (cache[x][y][sum] != -1) {
			return cache[x][y][sum];
		}

		sum += triangle[x][y];

		return cache[x][y][sum] = Math.max(path1(x+1, y+1, sum), path1(x+1, y+1, sum));
	}

	int [][] cache2 = new int[100][100];

	/**
	 * (x,y) 에서 맨아래까지 내려가는 최대합은, sum 과는 상관이 없음
	 * @param x
	 * @param y
	 * @return
	 */
	public int path2(int x, int y) {
		// base case
		if (x == n - 1) {
			return triangle[x][y];
		}

		if (cache2[x][y] != -1) {
			return cache2[x][y];
		}

		// 핵심 부분
		// 현재 경로 이전까지의 sum 만 넘어옴
		return cache2[x][y] = Math.max(path2(x+1, y), path2(x+1, y+1)) + triangle[x][y];
	}

}

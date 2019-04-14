package strategies.dp.numberOfCase;

import java.util.Arrays;

/**
 * 삼각형 위의 최대 경로 개수 세기
 *
 * @author baejunbeom
 */
public class TriPathCnt {

	private static final int [][] countCache = new int[100][100];

	private static final int N = 4;

	int triangle[][] = {
		{9},
		{5,7},
		{1,3,2},
		{3,5,5,6}
	};

	public int count(int y, int x) {
		// 1. base case : 가장 마지막 줄까지 내려간 경우.
		if (y == N-1) {
			return 1;
		}

		if (countCache[y][x] != -1) {
			return countCache[y][x];
		}

		int ret = 0;

		if (path2(y+1, x+1) >= path2(y+1, x)) {
			ret += count(y+1, x+1);
		}

		if (path2(y+1, x+1) <= path2(y+1, x)) {
			ret += count(y+1, x);
		}

		return countCache[y][x] = ret;
	}

	static int [][] cache2 = new int[100][100];

	/**
	 * (x,y) 에서 맨아래까지 내려가는 최대합은, sum 과는 상관이 없음
	 * @param x
	 * @param y
	 * @return
	 */
	public int path2(int x, int y) {
		// base case
		if (x == N - 1) {
			return triangle[x][y];
		}

		if (cache2[x][y] != -1) {
			return cache2[x][y];
		}

		// 핵심 부분
		// 현재 경로 이전까지의 sum 만 넘어옴
		return cache2[x][y] = Math.max(path2(x+1, y), path2(x+1, y+1)) + triangle[x][y];
	}

	public static void main(String[] args) {
		for (int [] cc : countCache) {
			Arrays.fill(cc, -1);
		}

		for (int [] c2 : cache2) {
			Arrays.fill(c2, -1);
		}

		TriPathCnt triPathCnt = new TriPathCnt();
		int count = triPathCnt.count(0, 0);

		System.out.println(count);
	}

}

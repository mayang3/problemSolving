package hackerrank.cs.algorithm.implement;

import java.util.*;

public class FormingAMagicSquare2 {
	// 9! 에 해당하는 magicSquare 를 로드시 미리 생성해 둔다.
	static List<int[][]> MAT_LIST = new ArrayList<>();

	static {
		int [][] m = new int[3][3];

		for (int [] mm : m) {
			Arrays.fill(mm, -1);
		}

		LinkedList<Integer> ll = new LinkedList<>();

		for (int i = 1; i < 10; i++) {
			ll.add(i);
		}

		solve(m, ll);
	}

	static class YX {
		int y;

		int x;
		YX(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static boolean verify(int [][] m) {

		for (int i = 0; i < 3; i++) {
			int horizon = 0;
			int vertical = 0;

			for (int j = 0; j < 3; j++) {
				horizon += m[i][j];
				vertical += m[j][i];
			}

			if (horizon != 15 || vertical != 15) {
				return false;
			}
		}

		if (m[0][0] + m[1][1] + m[2][2] != 15) {
			return false;
		}

		// 요거 대각선의 합 구하는 기법이 있었던것 같은데.. 기억이 안나네..
		if (m[0][2] + m[1][1] + m[2][0] != 15) {
			return false;
		}

		return true;
	}

	static void solve(int [][] m, LinkedList<Integer> ll) {

		YX np = findNextPoint(m);

		if (np == null) {
			if (verify(m)) {
				MAT_LIST.add(m);
			}

			return;
		}

		int y = np.y;
		int x = np.x;
		int size = new Integer(ll.size());

		// 현재 자리에 숫자 하나를 두고, 다음자리에 놓을 숫자를 넘긴다.
		for (int i = 0; i < size; i++) {
			Integer poll = ll.poll();

			m[y][x] = poll;

			solve(m, new LinkedList<>(ll));

			ll.addLast(poll);
		}
	}

	private static YX findNextPoint(int[][] m) {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (m[y][x] == -1) {
					return new YX(y, x);
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int [][] mat = new int[3][3];

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				mat[y][x] = scanner.nextInt();
			}
		}

		int min = Integer.MAX_VALUE;

		for (int [][] m : MAT_LIST) {
			min = Math.min(min, diff(mat, m));
		}

		System.out.println(min);
	}

	static int diff(int[][] mat, int[][] m) {

		int sum = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sum += Math.abs(mat[i][j] - m[i][j]);
			}
		}

		return sum;
	}
}

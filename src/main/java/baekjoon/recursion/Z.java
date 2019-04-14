package baekjoon.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 아래와 같이 최초 풀어서 Aceept.. 하지만 개선 여지는 있음..
 *
 */
public class Z {
	static int r;
	static int c;
	static boolean find = false;

	static List<OffSet> offsetList = new ArrayList<>();

	static {
		offsetList.add(new OffSet(0, 0));
		offsetList.add(new OffSet(0, 1));
		offsetList.add(new OffSet(1, 0));
		offsetList.add(new OffSet(1, 1));
	}

	static class OffSet {
		int y;
		int x;

		OffSet(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}


	static int solve(int n, int y, int x) {
		if (find) {
			return 0;
		}

		if (n == 1) {
			int bsSum = 0;

			for (OffSet of : offsetList) {
				bsSum++;

				int ry = of.y + y;
				int rx = of.x + x;

				if (r == ry && c == rx) {
					find=true;
					break;
				}
			}

			return bsSum;
		}

		int sum = 0;

		int div = n/2;

		sum += solve(div, y-div, x-div);
		sum += solve(div, y-div, x+div);
		sum += solve(div, y+div, x-div);
		sum += solve(div, y+div, x+div);

		return sum;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = (int)Math.pow(2, scanner.nextInt());
		r = scanner.nextInt();
		c = scanner.nextInt();

		System.out.println(solve(n/2, n/2-1, n/2-1) - 1);
	}
}

package hackerrank.cs.algorithm.implement;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

/**
 * color 칠하지 말고.. 각 점마다 이전 최대값하교 비교해서 겹치는지 확인하자.
 * 만약 겹친다면, 둘중 더 큰값을 선택하고 나머지값을 밀어낸다.
 *
 * 결국, 모든 점에서 가능한 블럭의 조합을 계산해서 product 의 최대값을 구한다.
 * Ema's super computer
 */
public class EmaSuperComputer {
	static final char GOOD = 'G';

	static int n;
	static int m;

	static LinkedList<MaxYx> linkedList;

	static class MaxYx {
		Set<YX> yxSet = new HashSet<YX>();
		int max;

		MaxYx(int y, int x, int max) {
			yxSet.add(new YX(y,x));

			int cnt = max / 4;

			for (int i=1 ; i<=cnt ; i++) {
				yxSet.add(new YX(y + i, x));
				yxSet.add(new YX(y - i, x));
				yxSet.add(new YX(y, x + i));
				yxSet.add(new YX(y, x - i));
			}

			this.max = max;
		}

		boolean isOverlap(MaxYx maxYx) {
			for (YX yx : this.yxSet) {
				if (maxYx.yxSet.contains(yx)) {
					return true;
				}
			}

			return false;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("MaxYx{");
			sb.append("yxSet=").append(yxSet);
			sb.append(", max=").append(max);
			sb.append('}');
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();
		m = scanner.nextInt();

		char [][] G = new char[n][m];

		for (int y=0 ; y<n ; y++) {
			String S = scanner.next();
			for (int x=0 ; x<m ; x++) {
				G[y][x] = S.charAt(x);
			}
		}

		linkedList = new LinkedList<MaxYx>();

		solve(G, 0, 0);

		int max = 0;

		for (int i=0 ; i<linkedList.size() ; i++) {
			MaxYx maxYx = linkedList.get(i);

			for (int j=i+1 ; j<linkedList.size() ; j++) {
				MaxYx maxYx2 = linkedList.get(j);

				if (maxYx.isOverlap(maxYx2) == false) {
					max = Math.max(max, maxYx.max * maxYx2.max);
				}
			}
		}

		System.out.println(max);
	}


	static void solve(char[][] G, int y, int x) {
		YX yx = findNext(G, y, x);

		if (yx == null) {
			return;
		}

		int max = 1;

		for (int i=1 ; i<n && i<m ; i++) {
			// up
			if (isValid(G, yx.y - i, yx.x) == false) {
				break;
			}

			// down
			if (isValid(G, yx.y + i, yx.x) == false) {
				break;
			}

			// left
			if (isValid(G, yx.y, yx.x-i) == false) {
				break;
			}

			// right
			if (isValid(G, yx.y, yx.x+i) == false) {
				break;
			}

			max += 4;

			// !!) 가능한 모든 조합을 넣어준다.
			// 같은 점에도 허용되는 모든 넓이의 경우의 수에 대해서 전부 넣어준다.
			linkedList.add(new MaxYx(y,x,max));
		}

		// 1일 경우에도 빠지면 안됨
		if (max == 1) {
			linkedList.add(new MaxYx(y,x,max));
		}


		if (yx.y < n && yx.x + 1 < m) {
			solve(G, yx.y, yx.x+1);
		} else if (yx.y+1<n && yx.x == m-1) {
			solve(G, yx.y+1, 0);
		}
	}

	static boolean isValid(char [][] G, int y, int x) {
		if (((y >= 0 && y < n) && (x >= 0 && x < m)) == false) {
			return false;
		}

		return G[y][x] == GOOD;
	}

	static YX findNext(char[][] G, int nextY, int nextX) {

		int x = nextX;

		for (int y=nextY ; y<n ; y++) {
			while (x < m) {
				if (G[y][x] == GOOD) {
					return new YX(y, x);
				}

				x++;
			}

			x = 0;
		}

		return null;
	}

	static class YX {
		int y;
		int x;

		YX(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			YX yx = (YX)o;

			if (y != yx.y) {
				return false;
			}
			return x == yx.x;
		}

		@Override
		public int hashCode() {
			int result = y;
			result = 31 * result + x;
			return result;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("YX{");
			sb.append("y=").append(y);
			sb.append(", x=").append(x);
			sb.append('}');
			return sb.toString();
		}
	}
}

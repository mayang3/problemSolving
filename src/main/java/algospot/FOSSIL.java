package algospot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FOSSIL {

	static class Point {
		double y;
		double x;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Pair {
		Point a;
		Point b;

		public Pair(Point a, Point b) {
			this.a = a;
			this.b = b;
		}
	}

	static List<Pair> upper = new ArrayList<>();
	static List<Pair> lower = new ArrayList<>();

	// [a.x, b.x] 구간 안에 x가 포함되나 확인한다.
	static boolean between (Point a, Point b, double x) {
		return (a.x <= x && x <= b.x) || (b.x <= x && x <= a.x);
	}

	// (a,b) 선분과 주어진 위치의 수직선이 교차하는 위치를 반환한다.
	static double at(Point a, Point b, double x) {
		double dy = b.y - a.y;
		double dx = b.x - a.x;

		// 방정식에 의한 교점 구하기
		return a.y + dy * (x - a.x) / dx;
	}

	// 두 다각형의 교집합을 수직선으로 잘랐을 때 단면의 길이를 반환한다.
	static double vertical(double x) {
		double minUp = 1e20;
		double maxLow = -1e20;

		// 위 껍질의 선분을 순회하며 최소 y 좌표를 찾는다.
		for (int i = 0; i < upper.size(); i++) {
			if (between(upper.get(i).a, upper.get(i).b, x)) {
				minUp = Math.min(minUp, at(upper.get(i).a, upper.get(i).b, x));
			}
		}

		for (int i = 0; i < lower.size(); i++) {
			if (between(lower.get(i).a, lower.get(i).b, x)) {
				maxLow = Math.max(maxLow, at(lower.get(i).a, lower.get(i).b, x));
			}
		}

		return minUp - maxLow;
	}

	// hull 이 반시계방향으로 주어지므로,
	// 인접한 두 점에 대해 x 가 증가하는 방향이면 아래쪽 껍질, 감소하는 방향이면 위쪽 껍질이다.
	static void decompose(List<Point> hull) {
		int n = hull.size();

		for (int i = 0; i < n; i++) {
			// == 인 선분 즉, 수직인 선분은 무시한다.
			if (hull.get(i).x < hull.get((i+1) % n).x) {
				lower.add(new Pair(hull.get(i), hull.get((i+1) % n)));
			} else if ((hull.get(i).x > hull.get((i+1) % n).x)) {
				upper.add(new Pair(hull.get(i), hull.get((i+1) % n)));
			}
		}
	}

	static double solve(List<Point> hull1, List<Point> hull2) {
		// 수직선이 두 다각형을 모두 만나는 x 좌표의 범위를 구한다.
		double lo = Math.max(minX(hull1), minX(hull2));
		double hi = Math.min(maxX(hull1), maxX(hull2));

		// 두 다각형이 아예 겹치지 않는 경우
		// 좌우로 배치되어서 겹치지 않는 경우에 대한 처리를 해준다.
		// 상하로 겹치지 않는 부분은 맨 아래의 vertical 이 음수를 반환하기 때문에 여기서 처리된다.
		if (hi < lo) {
			return 0;
		}

		for (int iter = 0; iter < 100; iter++) {
			double aab = (lo * 2 + hi) / 3D;
			double abb = (lo + hi * 2) / 3D;

			if (vertical(aab) < vertical(abb)) {
				lo = aab;
			} else {
				hi = abb;
			}
		}

		return Math.max(0.0, vertical(hi));
	}

	static double minX(List<Point> hull1) {
		double min = Double.MAX_VALUE;

		for (Point p : hull1) {
			min = Math.min(min, p.x);
		}

		return min;
	}

	static double maxX(List<Point> hull2) {
		double max = Double.MIN_VALUE;

		for (Point p : hull2) {
			max = Math.max(max, p.x);
		}

		return max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		while (C-- > 0) {
			int N = scanner.nextInt();
			int M = scanner.nextInt();

			List<Point> hull1 = new ArrayList<>();
			List<Point> hull2 = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				hull1.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
			}

			for (int i = 0; i < M; i++) {
				hull2.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
			}

			upper = new ArrayList<>();
			lower = new ArrayList<>();

			decompose(hull1);
			decompose(hull2);

			System.out.println(solve(hull1, hull2));
		}
	}
}

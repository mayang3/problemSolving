package baekjoon.dnc;

import java.util.*;

/**
 * https://ko.wikipedia.org/wiki/%EC%B5%9C%EA%B7%BC%EC%A0%91_%EC%A0%90%EC%8C%8D_%EB%AC%B8%EC%A0%9C
 * https://casterian.net/archives/92
 * https://m.blog.naver.com/PostView.nhn?blogId=babobigi&logNo=220530321348&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F
 *
 * 비둘기집의 원리이다..
 */
@SuppressWarnings("ALL")
public class 가장가까운두점2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		List<Point> pointList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();

			pointList.add(new Point(y, x));
		}

		Collections.sort(pointList, (o1, o2) -> {
			if (o1.x == o2.x) {
				return 0;
			}

			return o1.x < o2.x ? -1 : 1;
		});

		long ret = dnc(pointList);

		System.out.println(ret);
	}

	static long dnc(List<Point> pointList) {
		int size = pointList.size();

		if (size == 2) {
			return pointList.get(0).powArea(pointList.get(1));
		} else if (size == 1) {
			return Long.MAX_VALUE;
		}

		int m = size / 2;

		long min = Math.min(dnc(pointList.subList(0, m+1)), dnc(pointList.subList(m+1, size)));

		// 아래 단계에서 mid 점과 거리 d (min) 이하인 stripList 들을 대상으로 골라내지 않는다면,
		// 답이 틀리게 된다.

		// 이유는 mid 와 멀리 떨어져 있는 양 두점끼리의 최단거리가 나올 수 있기 때문이다..
		// 아래 로직에서는 반드시 mid 에 걸쳐있는 두 점의 최단거리가 나와야 한다.
		List<Point> stripList = new ArrayList<>();

		for (int i=0 ; i<size ; i++) {
			if (Math.pow(pointList.get(i).x - pointList.get(m).x, 2) < min) {
				stripList.add(pointList.get(i));
			}
		}

		Collections.sort(stripList, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.y == o2.y) {
					return 0;
				}

				return o1.y < o2.y ? -1 : 1;
			}
		});

		// x 좌표 기준으로 +/- distance 안에 속해있는 stripList 들을 다시 한번 y 축으로 정렬한다음에,
		// 자기 자신보다 높은 좌표에 속한 점들을 6개만 확인하면 된다.
		for (int i=0 ; i<stripList.size() ; i++) {
			for (int j=i+1 ; j<i+7 && j<stripList.size() && (Math.pow(stripList.get(j).y - stripList.get(i).y, 2) < min) ; j++) {
				min = Math.min(min, stripList.get(i).powArea(stripList.get(j)));
			}
		}


		return min;
	}

	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		long powArea(Point point) {
			int dy = Math.abs(this.y - point.y);
			int dx = Math.abs(this.x - point.x);

			return (long)(Math.pow(dy,2) + Math.pow(dx, 2));
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Point point = (Point)o;

			if (y != point.y) {
				return false;
			}
			return x == point.x;
		}

		@Override
		public int hashCode() {
			int result = y;
			result = 31 * result + x;
			return result;
		}
	}
	
}

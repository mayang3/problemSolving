package baekjoon.dnc;

import java.util.*;

/**
 * 솔루션 ) 비둘기집의 원리
 * https://ko.wikipedia.org/wiki/%EC%B5%9C%EA%B7%BC%EC%A0%91_%EC%A0%90%EC%8C%8D_%EB%AC%B8%EC%A0%9C
 * https://casterian.net/archives/92
 * https://m.blog.naver.com/PostView.nhn?blogId=babobigi&logNo=220530321348&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F
 *
 * 틀린 이유 -> 이 로직의 마지막 3단계 에서는 두 점의 사이를 동시에 늘려가기 때문에,
 *
 * 두 점 사이의 모든 경우의 수를 해본다고 볼 수 없다.
 *
 */
public class 가장가까운두점 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		List<Point> pointList = new ArrayList<>();

		for (int i=0 ; i<n ; i++) {
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

	/**
	 * 왼쪽, 오른쪽으로 리스트를 분할했을때,
	 * 가운데 점을 거치지 않고도, 왼쪽 오른쪽을 가로지르는 최단거리가 있을 수 있음..
	 * @param pointList
	 * @return
	 */
	static long dnc(List<Point> pointList) {
		int size = pointList.size();

		if (size == 2) {
			return pointList.get(0).powArea(pointList.get(1));
		} else if (size == 1) {
			return Long.MAX_VALUE;
		}


		int m = size / 2;

		long min = Math.min(dnc(pointList.subList(0, m+1)), dnc(pointList.subList(m+1, size)));

		int l = m-1;
		int r = m+1;

		int ml = l;
		int mr = r;

		// mid 를 중심으로 left or right 로 확장하되,
		// 꼭 mid 점이 포함하지 않는 최단거리도 있을 수 있다..
		// 순서는,
		// 1. left , right 의 다음 점을 하나씩 늘려나가면서,
		// 2. 확정된 다음 점과, 현재 left or right 점 중에 가까운점을 선택한다.

		while (l >= 0 || r <= size-1) {
			if (l >= 0) {

				long dm = pointList.get(l).powArea(pointList.get(m));
				long dr = pointList.get(l).powArea(pointList.get(mr));

				if (dm < min) {
					min = dm;
					ml = l;
				}

				if (dr < min) {
					min = dr;
					ml = l;
				}

				l--;
			}

			if (r <= size-1) {
				long dm = pointList.get(r).powArea(pointList.get(m));
				long dl = pointList.get(r).powArea(pointList.get(ml));

				if (dm < min) {
					min = dm;
					mr = r;
				}

				if (dl < min) {
					min = dl;
					mr = r;
				}

				r++;
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
			int dy = this.y - point.y;
			int dx = this.x - point.x;

			long sum = (long)(Math.pow(dy,2) + Math.pow(dx, 2));

			// 원래는 Math.sqrt(sum) 을 해야하지만..
			// 문제에서 제곱값을 반환하라고 함..
			// 답을 정수로 떨구기 위해서인듯..
			return sum;
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

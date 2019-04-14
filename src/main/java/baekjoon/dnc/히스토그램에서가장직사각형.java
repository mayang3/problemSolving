package baekjoon.dnc;

import java.util.Scanner;

public class 히스토그램에서가장직사각형 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while(true) {

			String S = scanner.nextLine();

			if ("0".equals(S)) {
				break;
			}

			String[] sp = S.split(" ");

			long [] arr = new long[sp.length-1];

			for (int i=0 ; i<arr.length ; i++) {
				arr[i] = Long.parseLong(sp[i+1]);
			}

			System.out.println(dnc(0, arr.length-1, arr));
		}
	}


	static long dnc(int l, int r, long [] arr) {
		if (r-l == 0) {
			return arr[r];
		}

		int m = (l + r) / 2;

		long area = Math.max(dnc(l, m, arr), dnc(m+1, r, arr));

		// 가운데 넓이
		int l1 = m;
		int r1 = m;
		long height = arr[m];

		// 결국 이 loop 는 그래프의 x 축을 한번 순회하면서 전체 넓이를 비교한다.
		// 이렇게 해도 중간에 높이가 낮은 그래프가 있다면, 그 이후 있는 최대 넓이가 비교가 안될 수 있기 때문에,
		// dnc 전략을 써야 한다.
		// 그리고 방향은 항상, 높이가 높은 그래프쪽으로 가야한다.
		// 이에 대한 증명은 책을 참조.
		while (l < l1 || r1 < r) {

			// left 가 제일 왼쪽에 도달했거나, right 의 다음 높이가 left 의 다음 높이보다 같거나 큰 경우
			if (r1 < r && (l1 == l || arr[l1-1] <= arr[r1+1])) {
				r1++;
				height = Math.min(height, arr[r1]);
			} else {
				// right 가 제일 오른쪽에 도달했거나, left 의 다음 높이가 right 의 다음 높이보다 같거나 큰 경우
				l1--;
				height = Math.min(height, arr[l1]);
			}

			area = Math.max(area, (r1-l1+1) * height);
		}

		return area;
	}
}

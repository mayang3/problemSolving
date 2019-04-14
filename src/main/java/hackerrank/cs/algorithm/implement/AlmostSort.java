package hackerrank.cs.algorithm.implement;

import java.util.Arrays;
import java.util.Scanner;

public class AlmostSort {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];

		for (int i=0 ; i<n ; i++) {
			arr[i] = scanner.nextInt();
		}

		// condition 1. already sort

		if (isSorted(arr)) {
			System.out.println("yes");
			return;
		}

		Point point = findTwoPoint(arr);

		// condition 2. swap
		if (findSwap(point, arr)) {
			return;
		}

		// condition 3. reverse
		if (findReverse(point, arr)) {
			return;
		}

		// condition 4.
		System.out.println("no");
	}

	static boolean isSorted(int[] arr) {
		boolean sorted = true;

		for (int i=1 ; i<arr.length ; i++) {
			if (arr[i] < arr[i-1]) {
				sorted = false;
			}
		}
		return sorted;
	}

	static class Point {
		int i;
		int j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Point{");
			sb.append("i=").append(i);
			sb.append(", j=").append(j);
			sb.append('}');
			return sb.toString();
		}
	}

	static Point findTwoPoint(int [] arr) {
		int i=0;
		int j=arr.length-1;

		while (true) {
			// swap 이나 reverse 할 두개의 포인트를 찾지 못한 경우
			if (i>=j) {
				i = j;
				break;
			}

			// i 의 현재값보다 뒤의 값이 크고, j 의 현재값보다 앞의 값이 크다면 두 인덱스를 발견한 것이다.
			if (arr[i] > arr[i+1] && arr[j-1] > arr[j]) {
				break;
			}

			// 제대로 정렬되어 있다면 array 의 length 까지 탐색한다.
			if (i < arr.length-1 && arr[i] < arr[i+1]) {
				i++;
			}

			// 제대로 정렬되어있다면 array 의 0 번까지 탐색한다.
			if (j > 0 && arr[j-1] < arr[j]) {
				j--;
			}
		}

		// i 가 j 값보다 크다면 같은 값으로 보정
		if (i > j) {
			int t = i;
			i = j;
			j = t;
		}

		return new Point(i, j);
	}

	static boolean findSwap(Point twoPoint, int [] arr) {

		int i = twoPoint.i;
		int j = twoPoint.j;

		// swap 영역 없음
		if (i == j) {
			return false;
		}

		int [] swapArr = Arrays.copyOf(arr, arr.length);

		int temp = swapArr[i];
		swapArr[i] = swapArr[j];
		swapArr[j] = temp;

		if (isSorted(swapArr)) {
			// 출력 값은 인덱스 1부터
			twoPoint.i++;
			twoPoint.j++;
			System.out.println("yes");
			System.out.println("swap " + twoPoint.i + " " + twoPoint.j);
			return true;
		}

		return false;
	}

	static boolean findReverse(Point twoPoint, int[] arr) {

		int i = twoPoint.i;
		int j = twoPoint.j;

		// reverse 영역 없음
		if (i == j) {
			return false;
		}

		int [] reverseArr = Arrays.copyOf(arr, arr.length);

		while (i < j) {
			int temp = reverseArr[i];
			reverseArr[i] = reverseArr[j];
			reverseArr[j] = temp;

			i++;
			j--;
		}

		if (isSorted(reverseArr)) {
			// 출력 값은 인덱스 1부터
			twoPoint.i++;
			twoPoint.j++;
			System.out.println("yes");
			System.out.println("reverse " + twoPoint.i + " " + twoPoint.j);
			return true;
		}

		return false;
	}
}

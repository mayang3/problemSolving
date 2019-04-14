package hackerrank.cs.algorithm.implement;

import java.util.Scanner;

// https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
public class BiggerIsGreater2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			// 0. initial sequence
			char[] arr = scanner.next().toCharArray();

			// Find longest non-increasing suffix
			// 만약 1,2,3,4,5 와 같이 증가하는 permutation 이라면,
			// 맨 마지막 값인 5가 pivot 이 된다.
			int i = arr.length - 1;
			while (i > 0 && arr[i - 1] >= arr[i]) {
				i--;
			}
			// Now i is the head index of the suffix

			// Are we at the last permutation already?
			if (i <= 0) {
				System.out.println("no answer");
				continue;
			}

			// Let arr[i - 1] be the pivot
			// Find rightmost element that exceeds the pivot
			int j = arr.length - 1;
			while (arr[j] <= arr[i - 1]) {
				j--;
			}
			// Now the value arr[j] will become the new pivot
			// Assertion: j >= i

			// Swap the pivot with j
			char temp = arr[i - 1];
			arr[i - 1] = arr[j];
			arr[j] = temp;

			// Reverse the suffix
			// 이 부분은 swap 된 j 값에 상관없이 suffix 전체 reverse 이다.
			// pivot 값 이후로 나오는 값들을 전체 reverse 하자.
			j = arr.length - 1;
			while (i < j) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}

			StringBuilder builder = new StringBuilder();

			for (char v : arr) {
				builder.append(v);
			}

			System.out.println(builder.toString());
		}
	}

}

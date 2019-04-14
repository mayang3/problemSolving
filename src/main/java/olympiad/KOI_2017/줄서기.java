package olympiad.KOI_2017;

import java.util.*;

public class 줄서기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = n - i - 1;
		}

		for (int i = 0; i < m; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();

			arr[x-1]--;
			arr[y-1]++;
		}

		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < n; i++) {
			int num = n - arr[i];

			if (set.contains(num)) {
				System.out.println(-1);
				return;
			}

			set.add(num);
			arr[i] = num;
		}

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}

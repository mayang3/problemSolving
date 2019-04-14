package baekjoon.array;

import java.util.Scanner;

public class 음계 {

	static void solve(int [] input) {

		boolean asc = false;
		boolean des = false;

		for (int i=1 ; i<8 ; i++) {
			int ret = input[i] - input[i-1];

			if (ret < 0) {
				des = true;
			} else if (ret > 0) {
				asc = true;
			} else {
				des = asc = true;
			}
		}

		if (asc && des) {
			System.out.println("mixed");
		} else if (asc) {
			System.out.println("ascending");
		} else if (des) {
			System.out.println("descending");
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int [] input = new int[8];

		for (int i=0 ; i<8 ; i++) {
			input[i] = scanner.nextInt();
		}

		solve(input);
	}
}

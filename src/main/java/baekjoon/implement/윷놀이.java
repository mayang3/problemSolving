package baekjoon.implement;

import java.util.Scanner;

public class 윷놀이 {
	static char [] retArr = new char[5];

	static {
		retArr[0] = 'D';
		retArr[1] = 'C';
		retArr[2] = 'B';
		retArr[3] = 'A';
		retArr[4] = 'E';
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		for (int i=0 ; i<3 ; i++) {
			int sum = 0;
			for (int j=0 ; j<4 ; j++) {
				sum += scanner.nextInt();
			}

			System.out.println(retArr[sum]);
		}
	}
}

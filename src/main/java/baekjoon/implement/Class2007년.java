package baekjoon.implement;

import java.util.Scanner;

public class Class2007년 {
	static int [] days = new int[13];
	static String [] week = new String[7];

	static {
		days[1] = 31;
		days[3] = 31;
		days[5] = 31;
		days[7] = 31;
		days[8] = 31;
		days[10] = 31;
		days[12] = 31;

		days[4] = 30;
		days[6] = 30;
		days[9] = 30;
		days[11] = 30;

		days[2] = 28;

		week[0] = "SUN";
		week[1] = "MON";
		week[2] = "TUE";
		week[3] = "WED";
		week[4] = "THU";
		week[5] = "FRI";
		week[6] = "SAT";

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int x = scanner.nextInt();
		int y = scanner.nextInt();

		int sum = 0;

		for (int i=0 ; i<x ; i++) {
			sum += days[i];
		}

		sum += y;

		System.out.println(week[sum % 7]);
	}
}

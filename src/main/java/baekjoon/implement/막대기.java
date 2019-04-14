package baekjoon.implement;

import java.util.LinkedList;
import java.util.Scanner;

public class 막대기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int X = scanner.nextInt();

		if (X == 64) {
			System.out.println(1);
			return;
		}

		LinkedList<Integer> sticks = new LinkedList<>();
		sticks.add(64);

		int cnt = 0;

		while (true) {
			int div = sticks.removeLast() / 2;

			int sum = 0;

			for (int i=0 ; i<sticks.size() ; i++) {
				sum += sticks.get(i);
			}


			if (sum + div == X) {
				cnt = sticks.size() + 1;
				break;
			}


			if (sum + div > X) {
				sticks.add(div);
			} else {
				sticks.add(div);
				sticks.add(div);
			}
		}

		System.out.println(cnt);
	}
}

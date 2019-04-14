package baekjoon.dnc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class 별찍기_10 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		List<String> dnc = dnc(n);

		for (String s : dnc) {
			System.out.println(s + " ");
		}

	}

	static List<String> dnc(int n) {
		if (n == 1) {
			return makeBaseCaseList();
		}

		int quotient = n / 3;

		List<String> dnc = dnc(quotient);

		List<String> newList = new LinkedList<>();

		// i 는 현재 단계에서 반복될 행 수
		for (int i = 0; i < n; i += dnc.size()) {
			// k 는 이전 단계에서의 String 행수
			for (int k = 0; k < dnc.size(); k++) {
				// j 는 현재 단계에서 반복될 컬럼 수
				for (int j = 0; j < n; j += dnc.size()) {
					// 존재 하지 않는다면 이전 단계의 String 을 그대로 넣어준다
					if (newList.isEmpty() || newList.size() - 1 < i+k) {
						newList.add(dnc.get(k));
					} else if (i == quotient && j == quotient) {
						// 공백을 출력할 구간에서는 quotient 의 길이만큼을 더해서 공백을 출력해준다.
						newList.add(i+k , newList.remove(i+k) + makeBlank(quotient));
					} else {
						// 그 외의 경우에는 현재값에서 증가시킬 이전단계의 값을 가져다가 계속 증가시킨다.
						newList.add(i+k, newList.remove(i+k) + dnc.get(k));
					}
				}
			}
		}

		return newList;
	}

	private static String makeBlank(int quotient) {
		StringBuilder sb = new StringBuilder();

		for (int i=0 ; i<quotient ; i++) {
			sb.append(" ");
		}

		return sb.toString();
	}

	private static List<String> makeBaseCaseList() {
		List<String> list = new ArrayList<>();
		list.add("*");
		return list;
	}
}

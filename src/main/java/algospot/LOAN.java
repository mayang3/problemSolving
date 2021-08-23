package algospot;

import java.util.Scanner;

public class LOAN {

	// amount 원을 연 이율 rates 퍼센트로 duration 월 간 한 달에 monthlyPayment 로 남았을 때 대출 잔금은?
	static double balance(double amount, int duration, double rates, double monthlyPayment) {
		double balance = amount;

		for (int i = 0; i < duration; i++) {
			// 이자가 붙는다.
			balance *= (1D + (rates / 12D) / 100D);
			// 상환액을 잔금에서 제한다.
			balance -= monthlyPayment;
		}

		return balance;
	}

	// amount 원을 연 이율 rates% 로 duration 월 간 갚으려면 한 달에 얼마씩 갚아야 하는가?
	static double payment(double amount, int duration, double rates) {
		// 불변 조건
		// 1. lo 원씩 갚으면 duration 개월 안에 갚을 수 있다.
		// 2. hi 원씩 갚으면 duration 개월 안에 갚을 수 있다.

		double lo = 0;
		double hi = amount * (1D + (rates / 12D) / 100D);

		for (int i = 0; i < 100; i++) {
			double mid = (lo + hi) / 2D;

			if (balance(amount, duration, rates, mid) <= 0) {
				hi = mid;
			} else {
				lo = mid;
			}
		}

		return hi;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			double N = scanner.nextDouble(); // 전세금 (amount)
			int M = scanner.nextInt(); // 개월수 (duration)
			double P = scanner.nextDouble(); // 연이율 (rates)

			System.out.println(payment(N, M, P));
		}
	}
}

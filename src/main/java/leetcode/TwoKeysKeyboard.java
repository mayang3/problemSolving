package leetcode;

public class TwoKeysKeyboard {
	public int minSteps(int n) {
		int res = 0;
		// i 는 copy 본을 반복하는 횟수

		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				res += i;
				n = n / i; // 현재 copy 된 수가 다음 부분 문제로써의 n 이 된다.
			}
		}

		return res;
	}

	public static void main(String[] args) {
		TwoKeysKeyboard twoKeysKeyboard = new TwoKeysKeyboard();

		System.out.println(twoKeysKeyboard.minSteps(7));
	}
}

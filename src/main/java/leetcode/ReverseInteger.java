package leetcode;

public class ReverseInteger {
	public int reverse(int x) {
		if (x == 0) {
			return 0;
		}

		String plusMinus = "";

		if (x < 0) {
			plusMinus = "-";
		}

		try {
			return Integer.parseInt(plusMinus + solve(Math.abs((long)x)));
		} catch (Exception e) {
		}

		return 0;
	}

	private String solve(long x) {
		if (x == 0) {
			return "";
		}

		return x % 10 + solve(x / 10);
	}

	public static void main(String[] args) {
		ReverseInteger ri = new ReverseInteger();
		int res = ri.reverse(1534236469);

		System.out.println(res);
	}
}

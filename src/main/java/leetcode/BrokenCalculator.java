package leetcode;

public class BrokenCalculator {
	public int brokenCalc(int X, int Y) {
		int ans = 0;

		// Y>X 인 경우에 답은 greedy 방식으로 풀 수 있다.
		// Y 가 짝수이면 항상 2로 나누는 것이 정답이 되고,
		// Y 가 홀수이면 항상 1을 더하는 것이 정답이 된다.
		while (Y > X) {
			Y = Y % 2 > 0 ? Y + 1 : Y / 2;
			ans++;
		}

		// 여기서 ans 에 X-Y 를 더해준 경우는,
		// Y <= X 인 경우에 답은 X-Y 이기 때문이다.
		return ans + X - Y;
	}

	public static void main(String[] args) {
		BrokenCalculator brokenCalculator = new BrokenCalculator();

		System.out.println(brokenCalculator.brokenCalc(2,3));
	}
}

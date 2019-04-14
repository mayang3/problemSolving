package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 *
 * n is the number of columns, n%2 will give you either 1 or 0 depending on if it is divisible by 2 or not. The same will be done with the rows using m and m%2.
 *
 * This gives us (n+n%2) (which we can think of as N from here on out) is even, and (m+m%2) (which we can think of as M) is also even, and although it might be larger than the original n*m board, it makes it easier to calculate the number of bases needed since if we had an odd number we would have to add 1 base on the last row or column.
 *
 * With our new N*M board, it is easy to solve since each base can fit 4 squares, 2 from the N and 2 from the M. So we do N*M/4 to get the number of bases needed.
 * 
 */
public class ArmyGame {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		// 여기서 핵심은 공급 드론이 4개의 블록을 커버할 수있다는 것이다.
		// 따라서 그리드가 2*2 인 경우에는 1개의 drop으로 충분하다.
		// 하지만 그리도가 3*2 라면 어떻게 될까?
		// 1 supply drop 은 그리드의 2*2 부분을 덮지만 여전히 1*2 부분이 있으므로 어쨌든 supply drop 이 추가로 필요하다.
		// 4 * 2 그리드에서 필요한 수와 같다.
		// 마찬가지로 지역이 3 * 3 인 경우 4 * 4 그리드에서 필요한만큼 방울을 필요로 한다.

		// 그렇지 않으면 일부 블록이 비어있게 된다.
		// 따라서 계산의 단순화를 위해 홀수를 가장 가까운 가장 큰 짝수 (예, 홀수 + 1 ) 로 증분한 다음에,
		// n * m /4 를 하면 된다.
		int ans = (n + n % 2) * (m + m % 2) / 4;

		System.out.println(ans);
	}
}

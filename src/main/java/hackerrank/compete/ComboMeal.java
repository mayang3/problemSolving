package hackerrank.compete;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class ComboMeal {

	static int profit(int b, int s, int c) {
		for (int fix=1 ; fix<b ; fix++) {
			int cb = b - fix;
			int cs = s - fix;

			if ((cb + cs + fix) == c) {
				return fix;
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int a0 = 0; a0 < t; a0++){
			int b = in.nextInt();
			int s = in.nextInt();
			int c = in.nextInt();
			int result = profit(b, s, c);
			System.out.println(result);
		}
		in.close();
	}
}

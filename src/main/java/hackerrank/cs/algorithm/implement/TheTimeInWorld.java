package hackerrank.cs.algorithm.implement;

import java.util.Scanner;

public class TheTimeInWorld {
	static String [] hMsg = new String[13];
	static String [] mMsg = new String[61];

	static {
		putHourMsg();
		putMinuteMsg();
	}

	private static void putMinuteMsg() {
		// 0~15
		mMsg[0] = "%s o' clock";
		mMsg[1] = "one minute past %s";
		mMsg[2] = "two minutes past %s";
		mMsg[3] = "three minutes past %s";
		mMsg[4] = "four minutes past %s";
		mMsg[5] = "five minutes past %s";
		mMsg[6] = "six minutes past %s";
		mMsg[7] = "seven minutes past %s";
		mMsg[8] = "eight minutes past %s";
		mMsg[9] = "nine minutes past %s";
		mMsg[10] = "ten minutes past %s";
		mMsg[11] = "eleven minutes past %s";
		mMsg[12] = "twelve minutes past %s";
		mMsg[13] = "thirteen minutes past %s";
		mMsg[14] = "fourteen minutes past %s";
		mMsg[15] = "quarter past %s";

		// 16~30
		mMsg[16] = "sixteen minutes past %s";
		mMsg[17] = "seventeen minutes past %s";
		mMsg[18] = "eighteen minutes past %s";
		mMsg[19] = "nineteen minutes past %s";
		mMsg[20] = "twenty minutes past %s";
		mMsg[21] = "twenty one minutes past %s";
		mMsg[22] = "twenty two minutes past %s";
		mMsg[23] = "twenty three minutes past %s";
		mMsg[24] = "twenty four minutes past %s";
		mMsg[25] = "twenty five minutes past %s";
		mMsg[26] = "twenty six minutes past %s";
		mMsg[27] = "twenty seven minutes past %s";
		mMsg[28] = "twenty eight minutes past %s";
		mMsg[29] = "twenty nine minutes past %s";
		mMsg[30] = "half past %s";

		// 31~45
		mMsg[31] = "twenty nine minutes to %s";
		mMsg[32] = "twenty eight minutes to %s";
		mMsg[33] = "twenty seven minutes to %s";
		mMsg[34] = "twenty six minutes to %s";
		mMsg[35] = "twenty five minutes to %s";
		mMsg[36] = "twenty four minutes to %s";
		mMsg[37] = "twenty three minutes to %s";
		mMsg[38] = "twenty three minutes to %s";
		mMsg[39] = "twenty two minutes to %s";
		mMsg[40] = "twenty minutes to %s";
		mMsg[41] = "nineteen minutes to %s";
		mMsg[42] = "eighteen minutes to %s";
		mMsg[43] = "seventeen minutes to %s";
		mMsg[44] = "sixteen minutes to %s";
		mMsg[45] = "quarter to %s";

		// 46~59
		mMsg[46] = "fourteen minutes to %s";
		mMsg[47] = "thirteen minutes to %s";
		mMsg[48] = "twelve minutes to %s";
		mMsg[49] = "eleven minutes to %s";
		mMsg[50] = "ten minutes to %s";
		mMsg[51] = "nin minutes to %s";
		mMsg[52] = "eight minutes to %s";
		mMsg[53] = "seven minutes to %s";
		mMsg[54] = "six minutes to %s";
		mMsg[55] = "five minutes %s";
		mMsg[56] = "four minutes to %s";
		mMsg[57] = "three minutes to %s";
		mMsg[58] = "two minutes to %s";
		mMsg[59] = "one minute to %s";
	}

	private static void putHourMsg() {
		hMsg[1] = "one";
		hMsg[2] = "two";
		hMsg[3] = "three";
		hMsg[4] = "four";
		hMsg[5] = "five";
		hMsg[6] = "six";
		hMsg[7] = "seven";
		hMsg[8] = "eight";
		hMsg[9] = "nine";
		hMsg[10] = "ten";
		hMsg[11] = "eleven";
		hMsg[12] = "twelve";
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int H = scanner.nextInt();
		int M = scanner.nextInt();

		// 30 minutes 단위로 미래형과 과거형이 나뉜다.
		H = M > 30 ? H+1 : H;

		System.out.println(String.format(mMsg[M], hMsg[H]));
	}

}

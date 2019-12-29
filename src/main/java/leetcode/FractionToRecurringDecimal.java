package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}

		StringBuilder sb = new StringBuilder();

		// + or -
		sb.append((numerator > 0) ^ (denominator > 0) ? "-" : "");

		// abs
		long numer = Math.abs(numerator);
		long deno = Math.abs(denominator);

		// integral part
		sb.append(numer / deno);

		numer %= deno;
		if (numer == 0) {
			return sb.toString();
		}

		// fraction part
		sb.append(".");

		// store remainder,index pair
		Map<Long, Integer> map = new HashMap<>();
		// 나머지의 수와 index 를 기록해둔다.
		map.put(numer, sb.length());
		// 0 이 되어야만 소수점이 딱 나누어 떨어진다.
		while (numer != 0) {
			numer *= 10;
			sb.append(numer / deno);
			numer %= deno;

			if (map.containsKey(numer)) {
				int index = map.get(numer);

				sb.insert(index,"(");
				sb.append(")");
				break;

			} else {
				map.put(numer, sb.length());
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		FractionToRecurringDecimal fraction = new FractionToRecurringDecimal();
		String res = fraction.fractionToDecimal(2, 3);

		System.out.println(res);
	}
}

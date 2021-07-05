package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 */
public class IntegerToRoman {

	static Map <Integer, String> mappings = new TreeMap<>();

	static {
		mappings.put(1, "I");
		mappings.put(5, "V");
		mappings.put(10, "X");
		mappings.put(50, "L");
		mappings.put(100, "C");
		mappings.put(500, "D");
		mappings.put(1000, "M");
	}

	public String intToRoman(Integer num) {

		StringBuilder sb = new StringBuilder();

		int divisor = 1000;

		while (divisor > 0) {
			int quotient = num / divisor;

			if (quotient > 0) {
				sb.append(makeRoman(quotient, divisor));
			}

			num %= divisor;
			divisor /= 10;
		}

		return sb.toString();
	}

	private String makeRoman(int quotient, int divisor) {
		String roman = "";

		while (quotient > 0) {
			if (quotient == 4 || quotient == 9) {
				return roman + mappings.get(divisor) + mappings.get((quotient + 1) * divisor);
			}

			if (quotient >= 5) {
				roman += mappings.getOrDefault(5 * divisor, "");
				quotient -=5;
			} else {
				roman += mappings.getOrDefault(divisor, "");
				quotient -= 1;
			}
		}

		return roman;
	}

	public static void main(String[] args) {
		IntegerToRoman integerToRoman = new IntegerToRoman();

		assert integerToRoman.intToRoman(3).equals("III");
		assert integerToRoman.intToRoman(4).equals("IV");
		assert integerToRoman.intToRoman(9).equals("IX");
		assert integerToRoman.intToRoman(58).equals("LVIII");
		assert integerToRoman.intToRoman(1994).equals("MCMXCIV");
		assert integerToRoman.intToRoman(20).equals("XX");
	}
}

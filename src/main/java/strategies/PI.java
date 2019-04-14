package strategies;

/**
 * @author baejunbeom
 */
public class PI {

	public static void main(String[] args) {
		PI pi = new PI();
		System.out.println(pi.find("12673939"));
	}

	public int find(String num) {

		// base case 1
		if (num == null || num.isEmpty()) {
			return 0;
		}

		// base case 2
		if (num.length() < 3) {
			return 0;
		}

		int min = 999999999;

		for (int i=3 ; i<=5 ; i++) {
			int sum = level(num.substring(0, Math.min(num.length()-1, i)));

			sum += find(num.substring(Math.min(num.length()-1, i), num.length()));

			min = Math.min(sum, min);
		}

		return min;
	}

	private int level(String substring) {

		char[] chars = substring.toCharArray();

		if (isLevelOne(chars)) {
			return 1;
		} else if (isLevelTwo(chars)) {
			return 2;
		} else if (isLevelFour(chars)) {
			return 4;
		} else if (isLevelFive(chars)) {
			return 5;
		} else {
			return 10;
		}

	}

	boolean isLevelFive(char[] chars) {
		int diff = (int)chars[1] - (int)chars[0];
		int diff2 = diff * -1;

		for (int i=0 ; i<chars.length-1 ; i++) {
			int re = (int)chars[i + 1] - (int)chars[i];

			if (re != diff && re != diff2) {
				return false;
			}
		}


		return true;
	}

	boolean isLevelFour(char[] chars) {
		int first = chars[0];
		int second = chars[1];

		for (int i=2 ; i<chars.length ; i++) {
			int v = chars[i];

			if (v != first && v != second) {
				return false;
			}
		}

		return true;
	}

	boolean isLevelTwo(char[] chars) {

		int i=0;
		int j=i+1;

		boolean inc = true;
		boolean dec = true;

		while(j<chars.length) {
			int first = chars[i];
			int second = chars[j];

			if (inc && second-first != 1) {
				inc = false;
			}

			if (dec && second-first != -1) {
				dec = false;
			}

			i++;
			j++;
		}

		if (inc || dec) {
			return true;
		}

		return false;
	}

	boolean isLevelOne(char[] inputs) {

		char temp = inputs[0];

		for (char input : inputs) {
			if (temp != input) {
				return false;
			}
		}

		return true;
	}

}

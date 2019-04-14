package strategies;

/**
 * @author baejunbeom
 */
public class Picnic {

	static boolean [][] matrix = new boolean[10][10];

	static {
		matrix[0][1] = true;
		matrix[1][0] = true;

		matrix[1][2] = true;
		matrix[2][1] = true;

		matrix[2][3] = true;
		matrix[3][2] = true;

		matrix[3][0] = true;
		matrix[0][3] = true;

		matrix[0][2] = true;
		matrix[2][0] = true;

		matrix[1][3] = true;
		matrix[3][1] = true;
	}


	public int countPairings(int n, boolean [] takenArr) {
		boolean finished = true;

		for (boolean taken : takenArr) {
			if (!taken) {
				finished = false;
				break;
			}
		}

		// base case  : 모든 친구들을 짝지었다면 하나의 경우의 수를 찾은 것
		if (finished) {
			return 1;
		}

		int result = 0;

		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<n ; j++) {
				if (!takenArr[i] && !takenArr[j] && matrix[i][j]) {
					takenArr[i] = takenArr[j] = true;
					result += countPairings(n,takenArr);
					takenArr[i] = takenArr[j] = false;
				}
			}
		}

		return result;
	}

	public int countPairings2(int n, boolean [] takenArr) {
		int first = -1;

		for (int i = 0 ; i<n ; i++) {
			if (!takenArr[i]) {
				first = i;
				break;
			}
		}

		// base case
		if (first == -1) {
			return 1;
		}

		int returnVal = 0;

		for (int i = first + 1 ; i<n ; i++) {
			if (!takenArr[i] && matrix[i][first]) {
				takenArr[first] = takenArr[i] = true;
				returnVal += countPairings2(n, takenArr);
				takenArr[first] = takenArr[i] = false;
			}
		}

		return returnVal;
	}

	public static void main(String[] args) {
		Picnic picnic = new Picnic();
		int i = picnic.countPairings2(4, new boolean[4]);
		System.out.println(i);
	}
}

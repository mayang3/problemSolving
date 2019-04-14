package strategies;

/**
 * @author baejunbeom
 */
public class DivideAndConquerMatrix {

	static class SquareMatrix {
		int size() {
			return 0;
		}
	}

	SquareMatrix identity(int n) {
		return null;
	}

	SquareMatrix pow(SquareMatrix A, int m) {
		// base case : A의 0승 = 1
		if (m==0) {
			return identity(A.size());
		}

		// 홀수인 경우
		if (m % 2 > 0) {
			return multiple(pow(A, m-1), A);
		}

		SquareMatrix half = pow(A, m/2);

		return multiple(half, half);
	}

	private SquareMatrix multiple(SquareMatrix A, SquareMatrix B) {
		return null;
	}

	public static void main(String[] args) {
		int [][] a = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};

		int [][] b = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};

	}

}

package leetcode;

public class MinimumDominoRotationsForEqualRow {
	public int minDominoRotations(int[] A, int[] B) {
		int n = A.length;

		int [] countA = new int[7];
		int [] countB = new int[7];
		int [] same = new int[7];

		for (int i = 0; i < n; i++) {
			countA[A[i]]++;
			countB[B[i]]++;
			if (A[i] == B[i]) {
				same[A[i]]++;
			}
		}

		int min = Integer.MAX_VALUE;

		for (int i = 1; i < 7; i++) {
			// equal to A union B like (A + B - (A & B))
			if (countA[i] + countB[i] - same[i] == n) {
				min = Math.min(min, Math.min(countA[i], countB[i]) - same[i]); // same 은 A 와 B 양쪽에 존재하므로 이 갯수를 빼준다.
			}
		}

		return min == Integer.MAX_VALUE ? -1 : min;
	}

	public static void main(String[] args) {
		int [] A = {2,1,2,4,2,2};
		int [] B = {5,2,6,2,3,2};

		MinimumDominoRotationsForEqualRow minimumDominoRotationsForEqualRow = new MinimumDominoRotationsForEqualRow();
		int res = minimumDominoRotationsForEqualRow.minDominoRotations(A, B);

		System.out.println(res);
	}
}

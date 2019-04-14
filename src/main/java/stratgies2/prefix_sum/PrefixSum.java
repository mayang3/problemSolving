package stratgies2.prefix_sum;

public class PrefixSum {

	static class PS {
		int [] partialSum;

		PS(int [] input) {
			partialSum = new int[input.length];

			partialSum[0] = input[0];

			for (int i = 1; i < input.length; i++) {
				partialSum[i] = partialSum[i-1] + input[i];
			}
		}

		int getRangeSum(int from, int to) {
			// from 이 0 이라면 처음부터 더한 값이므로,
			// partialSum[to] 만 구하면 된다.
			if (from == 0) {
				return partialSum[to];
			}

			// from ~ to 까지의 부분합을 구하고자 하면,
			// 0 ~ from -1 까지의 합을 partialSum[to] 에서 빼주어야만 한다.
			return partialSum[to] - partialSum[from-1];
		}
	}
}

package leetcode;

public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int surplus = 0;
		int deficit = 0;
		int startIndex = 0;

		for (int i = 0; i < gas.length; i++) {
			int sum = gas[i] + surplus - cost[i] ;

			if (sum > 0) {
				if (surplus == 0) {
					startIndex = i;
				}

				surplus = sum;
			} else {
				deficit += sum;
				surplus = 0;
			}
		}

		return surplus + deficit >= 0 ? startIndex : -1;
	}

	public static void main(String[] args) {
		int [] gas = {2, 3, 1, 2};
		int [] cost = {4, 2, 3, 1};

		GasStation gasStation = new GasStation();
		System.out.println(gasStation.canCompleteCircuit(gas, cost));
	}
}

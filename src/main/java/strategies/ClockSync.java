package strategies;

/**
 * @author baejunbeom
 */
public class ClockSync {
	int [][] switchInfo = {
		{0,1,2},
		{3,7,9,11},
		{4,10,14,15},
		{0,4,5,6,7},
		{6,7,8,10,12},
		{0,2,14,15},
		{3,14,15},
		{4,5,7,14,15},
		{1,2,3,4,5},
		{3,4,5,9,13}
	};

	private static final int MAX_VAL = 987654321;

	public int count(int [] clocks, int swtch) {

		// base case : 모든 스위치를 다 눌러본 경우
		if (swtch == 10) {
			return isClear(clocks) ? 0 : MAX_VAL;
		}

		int ret = MAX_VAL;

		for (int cnt=0 ; cnt<4 ; cnt++) {
			// 최초 자신의 스위치를 누르지 않고 다음 스위치를 먼저 눌러 본다
			ret = Math.min(ret, cnt + count(clocks, swtch + 1));
			// 그 후 자신의 스위치를 누른다
			push(clocks, swtch);
		}

		return ret;
	}

	/**
	 * swtch 번째의 스위치를 누른다.
	 *
	 * @param clocks
	 * @param swtch
	 */
	void push(int [] clocks, int swtch) {
		int[] clockConnectArray = switchInfo[swtch];

		for (int clockNo : clockConnectArray) {
			clocks[clockNo] += 3;
			if (clocks[clockNo] == 15) {
				clocks[clockNo] = 3;
			}
		}

	}

	boolean isClear(int[] clocks) {

		for (int clock : clocks) {
			if (clock != 12) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		// 1. base case : 모든 시계가 12시 인 경우
		// 현재까지 누른 스위치의 횟수를 리턴

		// 2. 각 스위치마다 4번씩 누를 수 있고
		// 2-1. 한번 누르는 단계마다 연결된 시계들의 시간을 바꾼다.
		// 2-2. 다음 스위치를 dfs 한다.
		// 2-3. 바꾼 시계들의 시간을 원복한다.

		// 3. 리턴받은 스위치의 횟수중에 가장 작은 횟수를 기록하여 리턴한다.
		final int [] clocks = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
		final int [] clocks2 = {12, 9, 3, 12, 6, 6, 9, 3, 12, 9, 12, 9, 12, 12, 6, 6};

		ClockSync clockSync = new ClockSync();
		int count = clockSync.count(clocks2, 0);

		System.out.println(count);
	}


}

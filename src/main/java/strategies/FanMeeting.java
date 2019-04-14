package strategies;

/**
 * @author baejunbeom
 */
public class FanMeeting {

	/**
	 * 최초 bruteforce 방법으로 작성
	 * @param idol
	 * @param fans
	 * @return
	 */
	public int bruteForce(char [] idol, char [] fans) {
		int ret = 0;

		for (int i=0 ; i<fans.length ; i++) {

			if (i+idol.length > fans.length) {
				break;
			}

			boolean success = true;

			for (int j=0 ; j<idol.length ; j++) {
				int k = j+i;

				if (fans[k] == 'M' && idol[j] == 'M') {
					success = false;
					break;
				}
			}

			if (success) {
				ret++;
			}
		}

		return ret;
	}


	public static void main(String[] args) {
		String idol = "FFFFF";
		String fan = "FFFFFFFFFF";

		FanMeeting fanMeeting = new FanMeeting();
		int count = fanMeeting.bruteForce(idol.toCharArray(), fan.toCharArray());

		System.out.println(count);
	}
}

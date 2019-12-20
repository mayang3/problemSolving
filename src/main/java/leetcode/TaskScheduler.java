package leetcode;

/**
 * @author neo82
 */
public class TaskScheduler {

	/**
	 *
	 * @param tasks
	 * @param n
	 * @return
	 */
	public int leastInterval(char[] tasks, int n) {
		int[] counter = new int[26];

		int max = 0; // 가장 많이 출현한 문자의 최대갯수
		int maxCount = 0; // 최대 개수가 같은 문자가 몇개인가.

		for(char task : tasks) {
			counter[task - 'A']++;
			if(max == counter[task - 'A']) {
				maxCount++;
			} else if(max < counter[task - 'A']) {
				max = counter[task - 'A'];
				maxCount = 1; // 더 큰 값이 들어오면 최대 개수가 같은 문자는 1개가 된다.
			}
		}

		int partCount = max - 1; // max 만큼 출현한 문자로 만들어지는 part 의 수. A ? ? A ? ? A 일때 partCount 는 2이다.
		int partLength = n - (maxCount - 1); // 한 part 의 길이, 즉, 한 part 내에서 ? 의 숫자.
		int emptySlots = partCount * partLength; // 총 empty 숫자
		int availableTasks = tasks.length - max * maxCount; // maxCount 만큼 출현한 문자들을 배열하고 난 후에, 배치 가능한 task 들
		int idles = Math.max(0, emptySlots - availableTasks); // idle 의 숫자. 만약 availableTask 가 더 많다면 idles 는 0 이 된다.

		return tasks.length + idles;
	}

	public static void main(String[] args) {

		// A B C A B D A C D E
		char [] tasks = {'A','A','A','B','B', 'C', 'C', 'D','D'};
		int n = 2;

		TaskScheduler ts = new TaskScheduler();
		int res = ts.leastInterval(tasks, n);

		System.out.println(res);
	}
}

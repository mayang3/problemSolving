package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author neo82
 */
public class ScheduleCourse3 {
	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, Comparator.comparingInt(a -> a[1])); //Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

		int time = 0;

		for (int[] c : courses) {
			time += c[0]; // add current course to a priority queue
			pq.add(c[0]);

			if (time > c[1]) {
				time -= pq.poll(); //If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
			}
		}

		return pq.size();
	}


	/*
	Assume [2,2],[1,3],[1,3],[1,3].

	Input is sorted by deadlines (tighter deadline comes first).

	time=0, you try fitting [2,2] it works.

	now time=2 try fitting [1,3] it works.
	now time=3. try fitting [1,3] it does not fit so remove the longest which is [2,2] so time=1 again.

	The point is if you could fit [1,3] when time=2, you can for sure fit it when time=1.
	That means you can safely remove elements you have added in the past anytime, in order to make more space for a combination of shorter ones..

	 */
	public static void main(String[] args) {

		// [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]

		// 반례
		// 기간순
		// [[5,5],[4,6],[2,6]]

		// 비용순
		// [[5,15],[3,19],[6,7],[2,10],[5,16],[8,14],[10,11],[2,19]]

		// 15, 19, 7, 10, 16, 14, 11, 19
		ScheduleCourse3 tt = new ScheduleCourse3();

		int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3000}};

		System.out.println(tt.scheduleCourse(courses));
	}
}

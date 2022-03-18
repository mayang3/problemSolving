package leetcode;

import java.util.*;

public class CourseSchedule3 {
	public int scheduleCourse(int[][] courses) {
		for (int i = 0; i < courses.length; i++) {
			for (int j = 0; j < courses.length; j++) {

			}
		}

		return 0;
	}

//	public int scheduleCourse(int[][] courses) {
//		Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
//
//		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
//
//		int curDay = 0;
//		int cnt = 0;
//		int max = 0;
//
//		for (int i = 0; i < courses.length; i++) {
//			int duration = courses[i][0];
//
//			if (duration > courses[i][1]) {
//				continue;
//			}
//
//			while (!pq.isEmpty() && curDay + duration > courses[i][1]) {
//				curDay -= pq.poll();
//				cnt--;
//				max = Math.max(max, cnt);
// 			}
//
//			curDay += duration;
//			cnt++;
//			max = Math.max(max, cnt);
//			pq.add(duration);
//		}
//
//		return max;
//	}

	public static void main(String[] args) {
		int [][] courses = {{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},{2,19}};

		CourseSchedule3	courseSchedule3 = new CourseSchedule3();
		System.out.println(courseSchedule3.scheduleCourse(courses));
	}
}

package algospot;

import java.util.*;

public class MeetingRoom {
	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();

		input.add(Arrays.asList(new Integer [] {0, 2}));
		input.add(Arrays.asList(new Integer [] {1, 2}));
		input.add(Arrays.asList(new Integer [] {2, 4}));
		input.add(Arrays.asList(new Integer [] {3, 5}));
		input.add(Arrays.asList(new Integer [] {3, 6}));
		input.add(Arrays.asList(new Integer [] {4, 8}));
		input.add(Arrays.asList(new Integer [] {5, 8}));

		MeetingRoom meetingRoom = new MeetingRoom();
		System.out.println(meetingRoom.schedule(input));
	}

	// index 0 is begin time of meeting
	// index 1 is end time of meeting
	public int schedule(List<List<Integer>> meetings) {
		Collections.sort(meetings, Comparator.comparingInt(o -> o.get(1)));

		int earliest = 0, selected = 0;

		for (int i = 0; i < meetings.size(); i++) {
			int begin = meetings.get(i).get(0);
			int end = meetings.get(i).get(1);

			if (earliest <= begin) {
				earliest = end;
				selected++;
			}
		}

		return selected;
	}
}

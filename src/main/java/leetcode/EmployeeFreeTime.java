package leetcode;

import java.util.*;

public class EmployeeFreeTime {

    public static void main(String[] args) {
        List<List<Interval>> schedule = new ArrayList<>();

        schedule.add(Arrays.asList(new Interval[] {new Interval(1, 2), new Interval(5, 6)}));
        schedule.add(Arrays.asList(new Interval[] {new Interval(1, 3)}));
        schedule.add(Arrays.asList(new Interval[] {new Interval(4, 10)}));

        EmployeeFreeTime employeeFreeTime = new EmployeeFreeTime();

        List<Interval> intervals = employeeFreeTime.employeeFreeTime(schedule);

        for (Interval interval : intervals) {
            System.out.println("---------------");
            System.out.println(interval.start);
            System.out.println(interval.end);
            System.out.println("---------------");
        }
    }

    static class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervalList = new ArrayList<>();

        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                intervalList.add(interval);
            }
        }

        Collections.sort(intervalList, Comparator.comparingInt(o -> o.start));

        PriorityQueue<Interval> pq = new PriorityQueue<>((o1, o2) -> o2.end - o1.end);
        pq.add(intervalList.get(0));

        for (int i = 1; i < intervalList.size(); i++) {
            Interval before = pq.poll();
            Interval current = intervalList.get(i);

            if (current.start < before.end) {
                before.end = Math.max(before.end, current.end);
            } else {
                pq.add(current);
            }

            pq.add(before);
        }

        LinkedList<Interval> res = new LinkedList<>();

        if (pq.isEmpty()) {
            return res;
        }

        Interval before = pq.poll();

        while (pq.isEmpty() == false) {
            Interval here = pq.poll();
            res.addFirst(new Interval(here.end, before.start));

            before = here;
        }

        return res;
    }
}

package leetcode;

import java.util.*;

public class SingleThreadedCPU {
    public int[] getOrder(int[][] tasks) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.processTime == o2.processTime) {
                return o1.index - o2.index;
            }

            return o1.processTime - o2.processTime;
        });

        List<Pair> pairList = new ArrayList<>();

        for (int i = 0; i < tasks.length; i++) {
            pairList.add(new Pair(i, tasks[i][0], tasks[i][1]));
        }

        Collections.sort(pairList, Comparator.comparingInt(o -> o.enqueueTime));

        int[] res = new int[tasks.length];

        int totalTime = pairList.get(0).enqueueTime;
        int taskIndex = 0;
        int answerIndex = 0;

        while (answerIndex < res.length) {
            while (taskIndex < pairList.size() && totalTime >= pairList.get(taskIndex).enqueueTime) {
                pq.add(pairList.get(taskIndex++));
            }

            if (pq.isEmpty()) {
                totalTime = pairList.get(taskIndex).enqueueTime;
                continue;
            }

            Pair poll = pq.poll();
            res[answerIndex++] = poll.index;
            totalTime += poll.processTime;
        }

        return res;
    }

    static class Pair {
        int index;
        int enqueueTime;
        int processTime;

        public Pair(int index, int enqueueTime, int processTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processTime = processTime;
        }
    }

    public static void main(String[] args) {
        int[][] tasks = {{35, 36}, {11, 7}, {15, 47}, {34, 2}, {47, 19}, {16, 14}, {19, 8}, {7, 34}, {38, 15}, {16, 18}, {27, 22}, {7, 15}, {43, 2}, {10, 5}, {5, 4}, {3, 11}};

        SingleThreadedCPU singleThreadedCPU = new SingleThreadedCPU();
        System.out.println(Arrays.toString(singleThreadedCPU.getOrder(tasks)));
    }
}

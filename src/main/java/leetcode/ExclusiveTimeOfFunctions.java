package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions {
	public int[] exclusiveTime(int n, List<String> logs) {
		int [] res = new int[n];

		Stack<Pair> stack = new Stack<>();

		for (String log : logs) {
			String [] split = log.split(":");

			int id = Integer.valueOf(split[0]);
			String op = split[1];
			int time = Integer.valueOf(split[2]);

			if (op.equals("start")) {
				stack.add(new Pair(time, 0));
			} else {
				Pair pop = stack.pop();

				int executeTime = time - pop.start + 1 - pop.child;

				res[id] += executeTime;

				if (stack.isEmpty() == false) {
					Pair correctPair = stack.pop();

					correctPair.child += executeTime + pop.child;

					stack.add(correctPair);
				}
			}
		}

		return res;
	}

	static class Pair {
		int start;
		int child;

		public Pair(int start, int child) {
			this.start = start;
			this.child = child;
		}
	}

	public static void main(String[] args) {
		int n = 3;
		String [] logs = {"0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5"};


		ExclusiveTimeOfFunctions exclusiveTimeOfFunctions = new ExclusiveTimeOfFunctions();
		System.out.println(Arrays.toString(exclusiveTimeOfFunctions.exclusiveTime(2, Arrays.asList(logs))));
	}
}

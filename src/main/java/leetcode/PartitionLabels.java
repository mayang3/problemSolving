package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
	public List<Integer> partitionLabels(String S) {
		List<Integer> res = new ArrayList<>();

		if (S == null || S.length() == 0) {
			return res;
		}

		int [] lastIndices = new int[26];

		// 각 문자의 마지막 인덱스를 기록한다.
		for (int i = 0; i < S.length(); i++) {
			lastIndices[S.charAt(i) - 'a'] = i;
		}

		int last = 0;
		int start = 0;

		for (int i = 0; i < S.length(); i++) {
			last = Math.max(last, lastIndices[S.charAt(i) - 'a']);

			if (last == i) {
				res.add(last - start + 1);
				start = last + 1;
				last = 0;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		PartitionLabels partitionLabels = new PartitionLabels();

		System.out.println(partitionLabels.partitionLabels("ababcbacadefegdehijhklij"));
	}
}

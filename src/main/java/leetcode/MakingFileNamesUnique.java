package leetcode;

import java.util.*;

public class MakingFileNamesUnique {
	public String[] getFolderNames(String[] names) {
		Map<String, Integer> dict = new HashMap<>();
		String [] res = new String[names.length];

		for (int i = 0; i < names.length; i++) {
			String name = names[i];

			if (dict.containsKey(name)) {
				// 1. 같은 키가 존재한다면, 해당 키가 가지고 있는 max 값 + 1 로 키값을 만든다.
				int freq = dict.get(name);

				dict.put(name, freq+1);
			}

			// 2. 같은 키가 존재하지 않는다면 그대로 출력한다.
			// 2-1. 이때 키 값에 괄호가 포함되어 있으면, 그 자체가 키로써 간주된다.
		}




		return res;
	}

	private String reconstruct(String k, int freq) {
		return k + "(" + freq + ")";
	}

	public static void main(String[] args) {
		String [] names = {"kaido","kaido(1)","kaido","kaido(1)"};

		MakingFileNamesUnique makingFileNamesUnique = new MakingFileNamesUnique();
		System.out.println(Arrays.toString(makingFileNamesUnique.getFolderNames(names)));
	}
}

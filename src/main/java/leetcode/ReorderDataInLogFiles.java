package leetcode;

import java.util.Arrays;

public class ReorderDataInLogFiles {
	public String[] reorderLogFiles(String[] logs) {

		Arrays.sort(logs, (o1, o2) -> {
			if (isLetterLogs(o1) && isLetterLogs(o2)) {
				String id1 = o1.split(" ")[0];
				String id2 = o2.split(" ")[0];

				String sub1 = o1.substring(id1.length());
				String sub2 = o2.substring(id2.length());

				if (sub1.equals(sub2)) {
					return id1.compareTo(id2);
				}

				return sub1.compareTo(sub2);
			}

			if (isLetterLogs(o1)) {
				return -1;
			} else if (isLetterLogs(o2)) {
				return 1;
			}

			return 0;
		});

		return logs;
	}

	private boolean isLetterLogs(String log) {
		String determine = log.split(" ")[1];

		for (int i = 0; i < determine.length(); i++) {
			char ch = determine.charAt(i);

			if (Character.isAlphabetic(ch)) {
				return true;
			} else {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String[] logs = {"j je", "b fjt", "7 zbr", "m le", "o 33"};

		ReorderDataInLogFiles reorderDataInLogFiles = new ReorderDataInLogFiles();

		String[] res = reorderDataInLogFiles.reorderLogFiles(logs);

		System.out.println(Arrays.toString(res));
	}
}

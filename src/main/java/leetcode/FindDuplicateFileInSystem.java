package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author neo82
 */
public class FindDuplicateFileInSystem {

	/**
	 *
	 * @param paths
	 * @return
	 */
	public List<List<String>> findDuplicate(String[] paths) {

		Map<String, List<String>> map = new HashMap<>();

		for (String path : paths) {
			String[] sp = path.split(" ");

			String dir = sp[0];

			for (int i = 1; i < sp.length; i++) {
				String fileNameWithContent = sp[i];

				int bracketIdx = fileNameWithContent.indexOf("(");

				String fileName = fileNameWithContent.substring(0, bracketIdx);
				String content = fileNameWithContent.substring(bracketIdx + 1, fileNameWithContent.length() - 1);

				map.computeIfAbsent(content, (dummy) -> new ArrayList<>()).add(dir + "/" + fileName);
			}
		}

		List<List<String>> res = new ArrayList<>();

		for (List<String> pathList : map.values()) {
			if (pathList.size() > 1) {
				res.add(pathList);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};

		FindDuplicateFileInSystem system = new FindDuplicateFileInSystem();
		List<List<String>> res = system.findDuplicate(paths);

		System.out.println(res);
	}
}

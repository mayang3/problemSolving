package leetcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author neo82
 */
public class FindDuplicateFileSystem_Improve {

	/**
	 *
	 * @param root
	 * @return
	 */
	public List<List<String>> findDuplicate(File root) {
		List<List<String>> ret = new ArrayList<>();

		Map<String, List<String>> mapByMD5 = new HashMap<>();

		findDuplicatedFilesByMD5(mapByMD5, root);

		for (List<String> list : mapByMD5.values()) {
			if (list.size() > 1) {
				ret.add(list);
			}
		}
		return ret;
	}

	/**
	 *
	 * @param map
	 * @param root
	 */
	private void findDuplicatedFilesByMD5(Map<String, List<String>> map, File root) {
		List<File> subDirectories = new ArrayList<>();

		for (File file : root.listFiles()) {
			if (file.isFile()) {
				findDuplicatedFilesByMD5FromFile(map, file);
			} else {
				subDirectories.add(file);
			}
		}

		for (File subDir : subDirectories) {
			findDuplicatedFilesByMD5(map, subDir);
		}
	}

	/**
	 *
	 * @param map
	 * @param file
	 */
	private void findDuplicatedFilesByMD5FromFile(Map<String, List<String>> map, File file) {
		String fileHash = makeHashQuick(file);

		List<String> list = map.get(fileHash);

		if (list == null) {
			list = new LinkedList<>();
			map.put(fileHash, list);
		}

		list.add(file.getAbsolutePath());
	}

	/**
	 *
	 * @param file
	 * @return
	 */
	private String makeHashQuick(File file) {
		try {
			FileInputStream fileInput = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fileInput.read(data); // 최초 file 의 길이만큼 한번에 읽어온다.
			fileInput.close();

			MessageDigest md = MessageDigest.getInstance("MD5"); // or SHA-1

			String fileHash = new BigInteger(1, md.digest(data)).toString(16); // fileHash

			return fileHash;
		} catch (IOException | NoSuchAlgorithmException e) {
			throw new RuntimeException("can't read file: " + file.getAbsolutePath(), e);
		}
	}

	public static void main(String[] args) {
		FindDuplicateFileSystem_Improve findDuplicateFileSystemImprove = new FindDuplicateFileSystem_Improve();
		List<List<String>> duplicate = findDuplicateFileSystemImprove.findDuplicate(new File("/Users/user/fileSystem"));

		System.out.println(duplicate);
	}
}

package leetcode;

import java.util.*;

public class DesignInMemoryFileSystem {

	static class FileSystem {

		static class Node {
			TreeMap<String, Node> children = new TreeMap<>();
			String val;
			String content;

			public Node(String val, String content) {
				this.val = val;
				this.content = content;
			}
		}

		Node root;

		public FileSystem() {
			this.root = new Node("/", null);
		}

		public List<String> ls(String path) {
			Node cur = this.root;

			String [] paths = path.split("/");

			for (int i = 1; i < paths.length; i++) {
				cur = cur.children.get(paths[i]);
			}

			List<String> res = new ArrayList<>();

			if (cur.content != null) {
				res.add(cur.val);
			} else {
				for (String curPath : cur.children.keySet()) {
					res.add(curPath);
				}
			}

			return res;
		}

		public void mkdir(String path) {
			Node cur = this.root;
			String [] paths = path.split("/");

			for (int i = 1; i < paths.length; i++) {
				String dir = paths[i];

				if (cur.children.get(dir) == null) {
					cur.children.put(dir, new Node(dir, null));
				}

				cur = cur.children.get(dir);
			}
		}

		public void addContentToFile(String filePath, String content) {
			Node cur = this.root;
			String [] paths = filePath.split("/");

			for (int i = 1; i < paths.length; i++) {
				String dirOrFile = paths[i];

				// in this case, it may be file.
				if (i == paths.length - 1) {
					cur.children.computeIfAbsent(dirOrFile, t -> new Node(dirOrFile, "")).content += content;
				} else {
					cur = cur.children.get(dirOrFile);
				}
			}
		}

		public String readContentFromFile(String filePath) {
			Node cur = this.root;
			String [] paths = filePath.split("/");

			for (int i = 1; i < paths.length; i++) {
				String dirOrFile = paths[i];

				// in this case, it may be file.
				if (i == paths.length - 1) {
					return cur.children.get(dirOrFile).content;
				} else {
					cur = cur.children.get(dirOrFile);
				}
			}

			return "";
		}
	}

	public static void main(String[] args) {
		FileSystem fs = new FileSystem();

		fs.mkdir("/goowmfn");
//		System.out.println(fs.ls("/goowmfn"));
//		System.out.println(fs.ls("/"));

		fs.mkdir("/z");
//		System.out.println(fs.ls("/"));

		fs.addContentToFile("/goowmfn/c", "hello");
		System.out.println(fs.ls("/goowmfn/c"));

	}
}

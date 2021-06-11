package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> deque = new LinkedList<>();

        String[] split = path.split("/");

        for (String s : split) {
            if (s == null || s.isEmpty() || s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                deque.pollLast();
            } else {
                deque.addLast(s);
            }
        }

        StringBuilder sb = new StringBuilder("/");

        while (deque.isEmpty() == false) {
            sb.append(deque.pollFirst());

            if (deque.size() > 0) {
                sb.append("/");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/"));
    }
}

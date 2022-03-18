package leetcode;

import java.util.*;

public class RemoveInvalidParentheses {
    static class Pair {
        int len;
        String s;

        public Pair(int len, String s) {
            this.len = len;
            this.s = s;
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o2.len - o1.len);
        pq.add(new Pair(0, ""));

        solve(pq, s, new StringBuilder(), 0);

        Set<String> res = new HashSet<>();

        while (pq.isEmpty() == false) {
            res.add(pq.poll().s);
        }

        return new ArrayList<>(res);
    }

    private void solve(PriorityQueue<Pair> pq, String s, StringBuilder sb, int i) {
        if (s.length() == i) {

            if (isValid(sb)) {
                if (pq.isEmpty() || pq.peek().len <= sb.length()) {
                    while (pq.isEmpty() == false && pq.peek().len < sb.length()) {
                        pq.poll();
                    }

                    pq.add(new Pair(sb.length(), sb.toString()));
                }
            }

            return;
        }

        char ch = s.charAt(i);

        // 현재 괄호를 넣지 않는 경우
        solve(pq, s, sb, i + 1);

        // 현재 괄호를 넣는 경우
        sb.append(ch);
        solve(pq, s, sb, i + 1);
        sb.deleteCharAt(sb.length()-1);
    }

    private boolean isValid(StringBuilder sb) {
        if (sb == null || sb.length() == 0) {
            return false;
        }

        int left = 0;
        int right = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (right > left) {
                return false;
            }

            if (sb.charAt(i) == '(') {
                left++;
            } else if (sb.charAt(i) == ')'){
                right++;
            }
        }

        return left == right;
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        System.out.println(removeInvalidParentheses.removeInvalidParentheses(")()("));
    }
}

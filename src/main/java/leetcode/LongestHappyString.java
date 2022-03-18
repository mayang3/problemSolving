package leetcode;

import java.util.PriorityQueue;

public class LongestHappyString {
    public static void main(String[] args) {
        LongestHappyString longestHappyString = new LongestHappyString();
        System.out.println(longestHappyString.longestDiverseString(7,1,0));
    }

    public String longestDiverseString(int a, int b, int c) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);

        if (a > 0) {
            pq.add(new Node('a', a));
        }

        if (b > 0) {
            pq.add(new Node('b', b));
        }

        if (c > 0) {
            pq.add(new Node('c', c));
        }

        StringBuilder sb = new StringBuilder();

        while (pq.isEmpty() == false) {
            Node node = pq.poll();

            char ch = node.c;

            if (node.count == 0) {
                continue;
            }

            // 만약 이미 두번 중목되어 있다면..
            if (sb.length() > 1 && sb.charAt(sb.length()-1) == ch && sb.charAt(sb.length()-2) == ch) {
                // 그 다음애를 연속해서 시도
                if (pq.isEmpty()) {
                    return sb.toString();
                }

                Node next = pq.poll();

                sb.append(next.c);
                next.count = next.count - 1;
                if (next.count > 0) {
                    pq.add(next);
                }

            } else {
                sb.append(ch);
                node.count = node.count - 1;
            }

            if (node.count > 0) {
                pq.add(node);
            }
        }

        return sb.toString();
    }

    static class Node {
        char c;
        int count;

        public Node(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}

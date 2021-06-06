package leetcode;

import java.util.*;

public class AutocompleteSystem {
    Trie trie;
    Map<String, Integer> timeMap = new HashMap<>();
    Node cur;

    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie(sentences);

        for (int i = 0; i < sentences.length; i++) {
            timeMap.put(sentences[i], times[i]);
        }

        this.cur = trie.root;
    }

    public List<String> input(char c) {
        Node node;

        if (c == '#') {
            node = this.cur;
            this.cur = trie.root;
        } else {
            node = trie.getNode(cur, c);
        }

        if (node == null) {
            if (c != '#') {
                this.cur = null;
            }
            return new ArrayList<>();
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            int res = o2.time - o1.time;

            if (res == 0) {
                return o1.word.compareTo(o2.word);
            }

            return res;
        });

        for (String word : node.words) {
            pq.add(new Pair(word, timeMap.get(word)));
        }

        List<String> res = new ArrayList<>();

        for (int i = 0; i < 3 && pq.isEmpty() == false; i++) {
            res.add(pq.poll().word);
        }

        if (node.endOfWord) {
            for (String word : node.words) {
                timeMap.put(word, timeMap.get(word)+1);
            }
        }

        this.cur = node;

        return res;
    }

    static class Pair {
        String word;
        int time;

        public Pair(String word, int time) {
            this.word = word;
            this.time = time;
        }
    }

    static class Node {
        Node [] children = new Node[27];
        boolean endOfWord;
        Set<String> words = new HashSet<>();
    }

    static class Trie {
        Node root = new Node();

        Trie(String [] sentences) {
            for (String sentence : sentences) {
                input(sentence);
            }
        }

        void input(String str) {
            Node cur = root;
            cur.words.add(str);

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                if (ch == ' ') {
                    ch = (char)('z' + 1);
                }

                if (cur.children[ch-'a'] == null) {
                    cur.children[ch-'a'] = new Node();
                }

                cur = cur.children[ch-'a'];
                cur.words.add(str);
            }

            cur.endOfWord = true;
        }

        Node getNode(Node cur, char ch) {
            if (cur == null) {
                return null;
            }

            if (ch == ' ') {
                ch = (char)('z' + 1);
            }

            if (cur.children[ch-'a'] == null) {
                return null;
            }

            cur = cur.children[ch-'a'];

            return cur;
        }
    }

    public static void main(String[] args) {
        String [] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int [] times = {5, 3, 2, 2};

        AutocompleteSystem obj = new AutocompleteSystem(sentences, times);

        System.out.println(obj.input('i'));
        System.out.println(obj.input(' '));
        System.out.println(obj.input('a'));
        System.out.println(obj.input('#'));

        System.out.println(obj.input('i'));
        System.out.println(obj.input(' '));
        System.out.println(obj.input('a'));
        System.out.println(obj.input('#'));

        System.out.println(obj.input('i'));
        System.out.println(obj.input(' '));
        System.out.println(obj.input('a'));
        System.out.println(obj.input('#'));
    }
}

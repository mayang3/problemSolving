package leetcode;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();

        for (int i = 0; i < words[0].length(); i++) {
            graph.put(words[0].charAt(i), new HashSet<>());
        }

        for (int i = 1; i < words.length; i++) {
            int j = 0;
            int k = 0;

            String s1 = words[i - 1];
            String s2 = words[i];


            while (j < s1.length() && k < s2.length() && s1.charAt(j) == s2.charAt(k)) {
                graph.computeIfAbsent(s1.charAt(j), t -> new HashSet<>());
                j++;
                k++;
            }

            if (j < s1.length() && k < s2.length()) {
                graph.computeIfAbsent(s1.charAt(j++), t -> new HashSet<>()).add(s2.charAt(k++));
            }

            if ((j == s1.length() || k == s2.length()) && s1.length() > s2.length()) {
                return "";
            }

            while (j < s1.length()) {
                graph.computeIfAbsent(s1.charAt(j++), t -> new HashSet<>());
            }

            while (k < s2.length()) {
                graph.computeIfAbsent(s2.charAt(k++), t -> new HashSet<>());
            }
        }

        // topological sort
        Deque<Character> deque = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        for (char node : graph.keySet()) {
            if (visited.contains(node) == false) {
                dfs(graph, deque, visited, node);
            }
        }

        return getStringAfterDetectingCycle(deque, graph);
    }

    private String getStringAfterDetectingCycle(Deque<Character> deque, Map<Character, Set<Character>> graph) {
        Map<Character, Integer> pos = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int ind = 0;

        while (deque.isEmpty() == false) {
            pos.put(deque.peekLast(), ind++);
            sb.append(deque.peekLast());
            deque.pollLast();
        }

        for (char parent : graph.keySet()) {
            for (char adj : graph.get(parent)) {
                if (pos.get(parent) > pos.get(adj)) {
                    // detect cycle
                    return "";
                }
            }
        }

        return sb.toString();
    }

    private void dfs(Map<Character, Set<Character>> graph, Deque<Character> deque, Set<Character> visited, char node) {
        visited.add(node);

        if (graph.containsKey(node)) {
            for (char adj : graph.get(node)) {
                if (visited.contains(adj) == false) {
                    dfs(graph, deque, visited, adj);
                }
            }
        }

        deque.add(node);
    }

    public static void main(String[] args) {
        // ["ac","ab","b"]
        String[] words = {"wnlb"};

        AlienDictionary alienDictionary = new AlienDictionary();
        System.out.println(alienDictionary.alienOrder(words));
    }
}

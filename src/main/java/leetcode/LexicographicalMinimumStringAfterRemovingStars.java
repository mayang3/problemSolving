package leetcode;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author neo82
 */
public class LexicographicalMinimumStringAfterRemovingStars {
    public String clearStars(String s) {
        boolean[] deleted = new boolean[s.length()];
        TreeMap<Character, TreeSet<Integer>> treeMap = new TreeMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                if (!treeMap.isEmpty()) {
                    deleted[i] = true;
                    Map.Entry<Character, TreeSet<Integer>> entry = treeMap.firstEntry();
                    Integer idx = entry.getValue().iterator().next();
                    deleted[idx] = true;
                    entry.getValue().remove(idx);

                    if (entry.getValue().isEmpty()) {
                        treeMap.remove(entry.getKey());
                    }
                }

            } else {
                treeMap.computeIfAbsent(s.charAt(i), t -> new TreeSet<>((o1, o2) -> o2 - o1)).add(i);
            }
        }

        return makeAnsString(s, deleted);
    }

    private String makeAnsString(String s, boolean[] deleted) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (deleted[i] || s.charAt(i) == '*') {
                continue;
            }

            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int k = 2;
        // 만약 k = 2 라고 하면, 7 에서 부터 값은 3이 되므로 5보다 작아진다.
        // {1011, 1111, 1011, 111}
        // 잘 생각해보자. 7에서
        int [] arr = {11, 15, 11, 7};

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            ans &= arr[i];
            System.out.println("----------------------------");
            System.out.println(Integer.toBinaryString(ans));
            System.out.println(ans);
            System.out.println("----------------------------");
        }

//        System.out.println(ans);
    }
}

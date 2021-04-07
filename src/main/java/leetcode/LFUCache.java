package leetcode;

import java.util.*;

public class LFUCache {

    Map<Integer, Integer> keyFreqMap;
    TreeMap<Integer, LinkedHashMap<Integer, Integer>> treeMap;
    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyFreqMap = new HashMap<>(capacity);
        this.treeMap = new TreeMap<>();
    }

    public int get(int key) {
        if (keyFreqMap.containsKey(key) == false) {
            return -1;
        }

        int freq = keyFreqMap.get(key);
        int val = treeMap.get(freq).remove(key);

        if (treeMap.get(freq).size() == 0) {
            treeMap.remove(freq);
        }

        keyFreqMap.put(key, freq+1);
        treeMap.computeIfAbsent(freq+1, t -> new LinkedHashMap<>()).put(key, val);

        return val;
    }

    public void put(int key, int value) {
        if (this.capacity == 0) {
            return;
        }

        // 크기가 꽉 찼다면 이전걸 지운다.
        if (keyFreqMap.containsKey(key) == false && keyFreqMap.size() == capacity) {
            LinkedHashMap<Integer, Integer> linkedHashMap = treeMap.firstEntry().getValue();
            int removeKey = new ArrayList<>(linkedHashMap.keySet()).get(0);
            linkedHashMap.remove(removeKey);
            keyFreqMap.remove(removeKey);
        }

        // 크기가 꽉 차지 않았다면 입력한다.
        keyFreqMap.merge(key, 1, Integer::sum);
        int freq = keyFreqMap.get(key);
        treeMap.computeIfAbsent(freq, t -> new LinkedHashMap<>()).put(key, value);

        // 이전에 입력된 위치에 값이 있다면 지워준다.
        if (freq > 1) {
            LinkedHashMap<Integer, Integer> linkedHashMap = treeMap.get(freq - 1);

            if (linkedHashMap != null) {
                linkedHashMap.remove(key);
            }

            if (treeMap.get(freq-1).size() == 0) {
                treeMap.remove(freq-1);
            }
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(0);

        lfuCache.put(0, 0);
        lfuCache.put(2, 2);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3,3);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4,4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));

    }
}

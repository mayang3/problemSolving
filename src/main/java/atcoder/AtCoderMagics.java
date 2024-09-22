package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class AtCoderMagics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        Pair[] pairs = new Pair[N];

        for (int i = 0; i < N; i++) {
            pairs[i] = new Pair(i+1, scanner.nextInt(), scanner.nextInt());
        }

        Arrays.sort(pairs, Comparator.comparingInt(o -> o.strength));
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            Pair here = pairs[i];

            NavigableMap<Integer, Integer> tailMap = treeMap.tailMap(here.cost, false);

            if (tailMap != null && !tailMap.isEmpty()) {
                Iterator<Integer> iterator = tailMap.keySet().iterator();
                while (iterator.hasNext()) {
                    int key = iterator.next();
                    iterator.remove();
                }
            }

            treeMap.put(here.cost, here.cardNum);
        }

        System.out.println(treeMap.size());

        List<Integer> cardNums = new ArrayList<>();
        cardNums.addAll(treeMap.values());

        Collections.sort(cardNums);

        for (int cardNum : cardNums) {
            System.out.print(cardNum + " ");
        }

    }

    static class Pair {
        int cardNum;
        int strength;
        int cost;

        public Pair(int cardNum, int strength, int cost) {
            this.cardNum = cardNum;
            this.strength = strength;
            this.cost = cost;
        }
    }
}

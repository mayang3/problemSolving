package stratgies2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Food {
    static int INF = 987654321;
    int M = 6;

    static List<Set<Integer>> foodTable = new ArrayList<>();

    static {
        // 페이
        foodTable.add(Stream.of(2,3,4)
                .collect(Collectors.toCollection(HashSet::new)));

        // 지아
        foodTable.add(Stream.of(5,6)
                .collect(Collectors.toCollection(HashSet::new)));

        // 민
        foodTable.add(Stream.of(1,3,5)
                .collect(Collectors.toCollection(HashSet::new)));

        // 수지
        foodTable.add(Stream.of(1,2,5)
                .collect(Collectors.toCollection(HashSet::new)));
    }

    boolean canEveryBodyEat(List<Integer> menu) {
        int cnt = 0;

        for (Set<Integer> set : foodTable) {
            for (int food : menu) {
                if (set.contains(food)) {
                    cnt++;
                    break;
                }
            }
        }

        return cnt == foodTable.size();
    }

    // food 번째 음식을 만들지 여부를 결정한다.
    int selectMenu(List<Integer> menu, int food) {
        if (food == M) {
            if (canEveryBodyEat(menu)) {
                return menu.size();
            }

            return INF;
        }

        int ret = selectMenu(menu, food + 1);

        menu.add(food);
        ret = Math.min(ret, selectMenu(menu, food+1));
        menu.remove(menu.size()-1);

        return ret;
    }

    public static void main(String[] args) {
        Food food = new Food();
        System.out.println(food.selectMenu(new ArrayList<>(), 1));
    }
}

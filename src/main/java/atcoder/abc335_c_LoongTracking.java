package atcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc335_c_LoongTracking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        List<int[]> coordinates = new ArrayList<>();

        for (int i = N; i >= 1; i--) {
            coordinates.add(new int[]{0, i});
        }

        for (int i = 0; i < Q; i++) {
            int C = scanner.nextInt();

            if (C == 1) {
                char D = scanner.next().charAt(0);

                int[] head = coordinates.get(coordinates.size() - 1);

                int[] newHead = new int[2];

                switch (D) {
                    case 'L':
                        newHead[0] = head[0];
                        newHead[1] = head[1] - 1;
                        break;
                    case 'R':
                        newHead[0] = head[0];
                        newHead[1] = head[1] + 1;
                        break;
                    case 'U':
                        newHead[0] = head[0] + 1;
                        newHead[1] = head[1];
                        break;
                    case 'D':
                        newHead[0] = head[0] - 1;
                        newHead[1] = head[1];
                        break;
                }

                coordinates.add(newHead);

                if (coordinates.size() - N > 100000) {
                    coordinates = coordinates.subList(N - coordinates.size(), coordinates.size());
                }

            } else {
                int pos = scanner.nextInt();

                System.out.println(coordinates.get(coordinates.size() - pos)[1] + " " + coordinates.get(coordinates.size() - pos)[0]);
            }
        }
    }
}

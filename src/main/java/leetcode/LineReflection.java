package leetcode;

import java.util.*;

public class LineReflection {
    public boolean isReflected(int[][] points) {
        if (points.length == 1 && points[0][0] == 0 && points[0][1] == 0) {
            return true;
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int [] p : points) {
            int x = p[0];
            int y = p[1];


        }

        return false;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    public static void main(String[] args) {
        int [][] points = {{1,1}, {-1,1}};

        LineReflection lr = new LineReflection();
        System.out.println(lr.isReflected(points));
    }
}

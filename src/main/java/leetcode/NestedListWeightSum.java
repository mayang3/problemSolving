package leetcode;

import java.util.List;

/**
 * @author neo82
 */
public class NestedListWeightSum {


    public int depthSum(List<NestedInteger> nestedList) {
        return solve(nestedList, 1);
    }

    private int solve(List<NestedInteger> nestedList, int depth) {

        int sum = 0;

        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += (nestedInteger.getInteger() * depth);
            } else {
                sum += solve(nestedInteger.getList(), depth + 1);
            }
        }

        return sum;
    }

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}



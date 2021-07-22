package leetcode;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        int totalCount = 0;

        SegmentTree segmentTree = new SegmentTree(Integer.MIN_VALUE, Integer.MAX_VALUE);

        for (int i = 1; i < nums.length; i++) {
            segmentTree.update(nums[i-1]);
            totalCount += segmentTree.getCount(nums[i] * 2L);
        }

        return totalCount;
    }

    static class SegmentTree {
        SegmentTree leftChild;
        SegmentTree rightChild;
        int count;
        long leftMin;
        long rightMax;

        public SegmentTree(long leftMin, long rightMax) {
            this.leftMin = leftMin;
            this.rightMax = rightMax;
        }

        void update(long val) {
            this.count++;

            if (leftMin == rightMax) {
                return;
            }

            long m = ((rightMax - leftMin) / 2) + leftMin;

            if (val <= m) {
                if (leftChild == null) {
                    leftChild = new SegmentTree(this.leftMin, m);
                }

                leftChild.update(val);
            } else {
                if (rightChild == null) {
                    rightChild = new SegmentTree(m+1, this.rightMax);
                }

                rightChild.update(val);
            }
        }

        int getCount(long val) {
            if (leftMin > val) {
                return count;
            }

            if (rightMax <= val) {
                return 0;
            }

            return (leftChild == null ? 0 : leftChild.getCount(val))
                    + (rightChild == null ? 0 : rightChild.getCount(val));
        }
    }

    public static void main(String[] args) {
        int [] nums = {2,4,3,5,1};

        ReversePairs reversePairs = new ReversePairs();
        System.out.println(reversePairs.reversePairs(nums));
    }
}

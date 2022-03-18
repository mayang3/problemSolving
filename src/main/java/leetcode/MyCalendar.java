package leetcode;

public class MyCalendar {

	SegmentTree segmentTree;
	public MyCalendar() {
		this.segmentTree = new SegmentTree(0, (int)1e9);
	}

	public boolean book(int start, int end) {
		if (segmentTree.query(start, end) == false) {
			return false;
		}

		segmentTree.book(start, end);
		return true;
	}

	public static void main(String[] args) {
		// ["MyCalendar","book","book","book","book","book","book","book","book","book","book"]
		//[[],[47,50],[33,41],[39,45],
		// [33,42],[25,32],[26,35],
		// [19,25],[3,8],[8,13],[18,27]]
		MyCalendar myCalendar = new MyCalendar();
		System.out.println(myCalendar.book(47, 50));
		System.out.println(myCalendar.book(33, 41));
		System.out.println(myCalendar.book(39, 45));

		System.out.println(myCalendar.book(33, 42));
		System.out.println(myCalendar.book(25, 32));
		System.out.println(myCalendar.book(26, 35));

		System.out.println(myCalendar.book(19, 25));
		System.out.println(myCalendar.book(3, 8));
		System.out.println(myCalendar.book(8, 13));
		System.out.println(myCalendar.book(18, 27));
	}

	static class SegmentTree {
		int min;
		int max;
		boolean canBook = true;
		SegmentTree left;
		SegmentTree right;

		public SegmentTree(int min, int max) {
			this.min = min;
			this.max = max;
		}

		void book(int start, int end) {
			this.canBook = false;

			if (min == max || end <= max || start >= min) {
				return;
			}

			int mid = min + (max - min) / 2;

			if (end <= mid) {
				left = new SegmentTree(min, mid);
				left.book(start, end);
			} else if (start >= mid) {
				right = new SegmentTree(mid+1, max);
				right.book(start, end);
			} else {
				left = new SegmentTree(min, mid);
				left.book(start, end);
				right = new SegmentTree(mid+1, max);
				right.book(start, end);
			}
		}

		boolean query(int start, int end) {
			if (start <= min && max <= end) {
				return this.canBook;
			}

			int mid = min + (max - min) / 2;

			if (start >= mid) {
				return this.right == null ? true : this.right.query(start, end);
			} else if (end <= mid) {
				return this.left == null ? true : this.left.query(start, end);
			}

			return (this.left == null ? true : this.left.query(start, end)) && (this.right == null ? true : this.right.query(start, end));
		}
	}
}

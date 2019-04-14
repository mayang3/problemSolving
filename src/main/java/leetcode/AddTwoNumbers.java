package leetcode;

/**
 * @author baejunbeom
 */
public class AddTwoNumbers {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			this.val = x;
		}

		@Override
		public String toString() {
			return "ListNode{" + "val=" + val + ", next=" + next + '}';
		}
	}

	/**
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		int add = 0;

		ListNode root = null;
		ListNode current = root;


		while (l1 != null || l2 != null) {
			int val1 = l1 == null ? 0 : l1.val;
			int val2 = l2 == null ? 0 : l2.val;

			int sum = val1 + val2 + add;
			int mod = sum % 10;
			int mok = sum / 10;

			if (mok > 0) {
				add = mok;
			} else {
				add = 0;
			}

			if (current == null) {
				root = new ListNode(mod);
				current = root;
			} else {
				current.next = new ListNode(mod);
				current = current.next;
			}

			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}

		if (l1 == null && l2 == null && add > 0) {
			current.next = new ListNode(add);
		}

		return root;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
		ListNode listNode = addTwoNumbers.addTwoNumbers(l1, l2);

		System.out.println(listNode);

	}
}

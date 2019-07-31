package leetcode;

public class MiddleOfTheLinkedList {
	public static void main(String[] args) {

		ListNode listNode5 = new ListNode(5);

		ListNode listNode4 = new ListNode(4);
		listNode4.next = listNode5;

		ListNode listNode3 = new ListNode(3);
		listNode3.next = listNode4;

		ListNode listNode2 = new ListNode(2);
		listNode2.next = listNode3;

		ListNode listNode1 = new ListNode(1);
		listNode1.next = listNode2;

		MiddleOfTheLinkedList middleOfTheLinkedList = new MiddleOfTheLinkedList();
		ListNode ret = middleOfTheLinkedList.middleNode(listNode1);

		System.out.println(ret.val);

	}

	 public static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) {
	     	this.val = x;
	     }
	 }

	public ListNode middleNode(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow;
	}
}

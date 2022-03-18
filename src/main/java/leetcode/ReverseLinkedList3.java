package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseLinkedList3 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        List<ListNode> list = new ArrayList<>();

        while (head != null) {
            list.add(head);
            head = head.next;
        }

        left--;
        right--;

        List<ListNode> leftSide = new ArrayList<>(list.subList(0, left));
        List<ListNode> middle = new ArrayList<>(list.subList(left, right+1));
        List<ListNode> rightSide = new ArrayList<>(list.subList(right+1, list.size()));

        Collections.reverse(middle);

        leftSide.addAll(middle);
        leftSide.addAll(rightSide);

        head = leftSide.get(0);
        ListNode cur = head;

        for (int i = 1; i < leftSide.size(); i++) {
            cur.next = leftSide.get(i);
            cur = cur.next;
        }

        cur.next = null;

        return head;
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode head = new ListNode(1, two);


        ReverseLinkedList3 reverseLinkedList3 = new ReverseLinkedList3();
        ListNode listNode = reverseLinkedList3.reverseBetween(head, 2, 4);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class SwappingNodesInaLinkedList {

    public static void main(String[] args) {

        ListNode listNode2 = new ListNode(90);
        ListNode listNode = new ListNode(100, listNode2);

        SwappingNodesInaLinkedList swappingNodesInaLinkedList = new SwappingNodesInaLinkedList();
        printAll(swappingNodesInaLinkedList.swapNodes(listNode, 2));
    }

    private static void printAll(ListNode swapNodes) {

        while (swapNodes != null) {
            System.out.println(swapNodes.val);
            swapNodes = swapNodes.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public ListNode swapNodes(ListNode head, int k) {
        return head;
    }
}

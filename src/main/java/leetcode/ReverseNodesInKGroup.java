package leetcode;

public class ReverseNodesInKGroup {

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

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newTail = head;
        ListNode cur = head;
        ListNode prev = null;
        int count = 0;

        while (count < k) {
            if (cur == null) {
                return head;
            }

            count++;
            cur = cur.next;
        }

        for (int i = 0; i < k && head != null; i++) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        if (head != null) {
            newTail.next = reverseKGroup(head, k);
        } else {
            newTail.next = null;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5, null);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);


        ReverseNodesInKGroup reverseNodesInKGroup = new ReverseNodesInKGroup();
        ListNode listNode = reverseNodesInKGroup.reverseKGroup(listNode1, 1);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
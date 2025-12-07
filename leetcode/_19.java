public class _19 {
    public static void main(String[] args) {

    }

    public class ListNode {
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

    // 暴力解法：但是用虚拟头指针，这样不用判断头部的情况
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode virtual = new ListNode(0);
        virtual.next = head;
        int length = 0;
        ListNode curr = virtual;
        while (curr != null) {
            length = length + 1;
            curr = curr.next;
        }
        int index = length - n;
        curr = virtual;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return virtual.next;

    }

    // 双指针
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = head;
        /**
         * 因为要删除倒数第n个，所以得找到倒数第n-1个，
         * 所以这里slow是指向dummy，而不是head，因为如果指向head，
         * 下面fast=null的时候，slow指向的是倒数第n个
         */
        ListNode slow = dummy;
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}

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

    // 暴力解法：先获得链表长度，然后就可以确定删除的位置
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length = length + 1;
            curr = curr.next;
        }
        if (length == n) {
            return head.next;
        }

        int index = length - n;
        curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return head;

    }

    // 暴力解法：但是用虚拟头指针，这样不用判断头部的情况
    public ListNode removeNthFromEnd2(ListNode head, int n) {
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
}

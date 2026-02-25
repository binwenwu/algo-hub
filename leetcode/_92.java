public class _92 {
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }

        // 先找到left的前一个节点
        ListNode dummy = new ListNode(0, head); // 哨兵节点
        ListNode p0 = dummy;
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }

        // 反转left和right之间的节点
        ListNode curr = p0.next;
        ListNode prev = null;
        ListNode temp;
        for (int i = 0; i < right - left + 1; i++) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // left指向curr，left的前一个节点指向prev
        p0.next.next = curr;
        p0.next = prev;

        return dummy.next;

    }

}

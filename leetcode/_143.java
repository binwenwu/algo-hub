
public class _143 {
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

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        // 1. 找中点（slow停在左中点）
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转后半部分，要保证左半边节点数量大于等于右半边，所以是 slow.next
        ListNode right = reverse(slow.next);
        slow.next = null; // 切断左右

        // 3. 合并两条链
        ListNode left = head;
        while (right != null) {
            ListNode lNext = left.next;
            ListNode rNext = right.next;

            left.next = right;
            right.next = lNext;

            left = lNext;
            right = rNext;
        }
    }

    // 反转链表
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}

class ListNode {
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

public class _143 {
    public static void main(String[] args) {
        _143 s = new _143();
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        s.reorderList(node1);
        ListNode curr = node1;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }

    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 1. 找到中间节点的右边一个节点（因为要保证左半边节点数量>=右半边)
        ListNode middle = middleNode(head);

        // 2. 反转右半边
        ListNode right = reverse(middle);

        // 3. 进行合并
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

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 因为要断开两半，所以这里先暂存起来
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode temp;
        ListNode curr = head;
        while (curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

}

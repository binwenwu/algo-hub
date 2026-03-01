public class _61 {

    /**
     * 步骤：
     * 
     * 求链表长度 n，同时找到尾节点
     * 尾巴连头 → 形成环
     * 找到新的尾节点
     * 断开环
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        // 1. 求长度 + 找尾巴
        int n = 1;
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
            n++;
        }

        // 2. 取模
        k %= n;
        if (k == 0) {
            return head;
        }

        // 3. 成环
        curr.next = head;

        // 4. 找新尾巴（走 n-k-1 步）
        curr = head;
        for (int i = 0; i < n - k - 1; i++) {
            curr = curr.next;
        }

        // 5. 断开
        ListNode res = curr.next;
        curr.next = null;

        return res;
    }
}

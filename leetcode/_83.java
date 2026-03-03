public class _83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next; // 删除重复
            } else {
                cur = cur.next; // 移动
            }
        }

        return head;
    }
}

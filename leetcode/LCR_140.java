public class LCR_140 {

    // 快慢指针
    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode fast = head;
        ListNode slow = head;

        // fast先走cnt步
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }

        // 同时移动
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}

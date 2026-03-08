public class _328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode oddHead = head;
        ListNode evenHead = head.next;

        ListNode odd = oddHead;
        ListNode even = evenHead;

        ListNode cur = head.next.next;
        int index = 3;

        while (cur != null) {
            if (index % 2 == 1) {
                odd.next = cur;
                odd = odd.next;
            } else {
                even.next = cur;
                even = even.next;
            }
            cur = cur.next;
            index++;
        }

        odd.next = evenHead;
        even.next = null;

        return oddHead;
    }
}

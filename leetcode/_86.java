public class _86 {

    /**
     * 构造两个链表，然后拼接
     * 
     * small：存 < x
     * big：存 >= x
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode bigDummy = new ListNode(0);

        ListNode small = smallDummy;
        ListNode big = bigDummy;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }

        // 关键：断开 big 尾巴
        big.next = null;

        // 拼接
        small.next = bigDummy.next;

        return smallDummy.next;
    }
}

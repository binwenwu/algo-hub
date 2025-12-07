
public class _25 {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        ListNode curr = head;
        while (curr != null) {
            n++;
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        ListNode pre = null;
        curr = head;

        for (int i = 0; i < n / k; i++) {
            for (int j = 0; j < k; j++) {
                ListNode temp = curr.next;
                curr.next = pre;
                pre = curr;
                curr = temp;
            }

            ListNode temp = p0.next;
            p0.next.next = curr;
            p0.next = pre;
            p0 = temp;
        }

        return dummy.next;
    }
}

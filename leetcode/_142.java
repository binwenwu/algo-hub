
public class _142 {
    public static void main(String[] args) {
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode start = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (start != fast) {
                    start = start.next;
                    fast = fast.next;
                }
                return fast;
            }
        }

        return null;
    }

}

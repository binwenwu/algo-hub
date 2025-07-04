
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

    public ListNode detectCycle1(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode left = head;
        ListNode right = head;
        ListNode temp = head;

        while (right != null && right.next != null) {
            left = left.next;
            right = right.next.next;
            if (right == left) {
                while (temp != right) {
                    temp = temp.next;
                    right = right.next;
                }
                return temp;
            }
        }

        return null;
    }

}

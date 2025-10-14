
public class _160 {
    public static void main(String[] args) {

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode curr = headA;
        while (curr != null) {
            lenA++;
            curr = curr.next;
        }
        curr = headB;
        while (curr != null) {
            lenB++;
            curr = curr.next;
        }

        if (lenA > lenB) {
            int idx = lenA - lenB;
            while (idx > 0) {
                headA = headA.next;
                idx--;
            }
        } else if (lenB > lenA) {
            int idx = lenB - lenA;
            while (idx > 0) {
                headB = headB.next;
                idx--;
            }
        }

        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    // 双指针解法
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }

        return A;
    }
}

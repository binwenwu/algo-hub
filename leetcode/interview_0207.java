public class interview_0207 {
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        int lengthA = 0, lengthB = 0;
        while (tempA != null) {
            tempA = tempA.next;
            lengthA = lengthA + 1;
        }
        while (tempB != null) {
            tempB = tempB.next;
            lengthB = lengthB + 1;
        }
        tempA = headA;
        tempB = headB;
        if (lengthA - lengthB >= 0) {
            for (int i = 0; i < lengthA - lengthB; i++) {
                tempA = tempA.next;
            }
        } else {
            for (int i = 0; i < lengthB - lengthA; i++) {
                tempB = tempB.next;
            }
        }

        while (tempA != null) {
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return null;

    }
}

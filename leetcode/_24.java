public class _24 {
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

    
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode temp = head;
        /**
         * 其实就是个虚拟头指针，不过这个头指针一开始指不指向head都无所谓，实际是
         * 用来存储当前节点的上一个节点而已
         */
        ListNode prev = new ListNode(0);
        head = head.next;
        while (curr != null && curr.next != null) {
            prev.next = curr.next;
            temp = curr.next.next;
            curr.next.next = curr;
            curr.next = temp;
            prev = curr;
            curr = temp;
        }

        return head;
    }

}

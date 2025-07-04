public class _203 {
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

    public ListNode removeElements1(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode temp = head;
      

        while (temp != null && temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

        return head;
    }

    // 设置虚拟头结点的方法
    public ListNode removeEListNode2(ListNode head, int val) {
        ListNode virtual = new ListNode();
        virtual.next = head;
        ListNode temp = virtual;
        while (virtual.next != null) {
            if (virtual.next.val == val) {
                virtual.next = virtual.next.next;
            } else {
                virtual = virtual.next;
            }
        }
        return temp.next;
    }
}

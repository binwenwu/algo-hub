public class _206 {

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

    // 双指针法，用一个temp临时保存当前节点的下一个节点，然后再改变
    // 当前节点的next指向
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode temp;
        ListNode prev = null;

        while (head != null) {
            temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;

        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    // 递归法，想象成每次就是两个大块，让第二个大块指向前面的大块，
    // 一开始是 null 和 head，最后的时候是我们需要的部分和一个 null
    public ListNode reverse(ListNode prev, ListNode curr) {
        if (curr == null) {
            return prev;
        }

        ListNode temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;

        return reverse(prev, curr);
    }

    // 递归法2，这个也是两个大块，但是第一次的时候，是head和head.next
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


}

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
        // 统计节点个数
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        ListNode pre = null;
        ListNode cur = head;

        // k 个一组处理
        for (; n >= k; n = n - k) {
            for (int i = 0; i < k; i++) { // 同 92 题
                ListNode temp = cur.next;
                cur.next = pre; // 每次循环只修改一个 next，方便大家理解
                pre = cur;
                cur = temp;
            }

            ListNode temp = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            p0 = temp;
        }

        return dummy.next;
    }
}

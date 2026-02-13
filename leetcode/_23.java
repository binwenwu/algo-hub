public class _23 {
    public static void main(String[] args) {

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode random;

        ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return lists[i];
        }
        int mid = (i + j) / 2;
        ListNode l1 = mergeKLists(lists, i, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, j);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}

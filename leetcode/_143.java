import java.util.ArrayList;
import java.util.List;

public class _143 {
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

    // 暴力解法
    public void reorderList1(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        curr = head;
        int left = 1;
        int right = list.size() - 1;
        int count = 0;
        while (left <= right) {
            if (count % 2 == 0) {
                curr.next = list.get(right);
                right--;
            } else {
                curr.next = list.get(left);
                left++;
            }
            count++;
            curr = curr.next;
        }

        curr.next = null;
    }

    // 把链表分半，然后后面一半反转，然后再重组链表
    public void reorderList2(ListNode head) {
        // 1. 找到链表的中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = slow.next; // 右半部分
        slow.next = null;
        ListNode left = head; // 左半部分

        // 2. 反转右半部分
        right = reverse(right);

        // 3. 重组链表
        // 因为左半边节点数大于右半边，所以只判断右边即可
        while (right != null) {
            ListNode l_temp = left.next;
            ListNode r_temp = right.next;
            left.next = right;
            left = l_temp;
            right.next = left;
            right = r_temp;
        }

    }
    
    public ListNode reverse(ListNode head) {
        ListNode temp;
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        return pre;
    }

}

import java.util.ArrayDeque;
import java.util.Deque;

public class _114 {
    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {

        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 头插法
    private TreeNode head;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = head; // 头插法，相当于链表的 root.next = head
        head = root; // 现在链表头节点是 root
    }

    // 使用迭代法进行先序遍历，同时进行修改
    public void flatten2(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
            node.left = null;
            node.right = stack.peek();
        }
    }
}

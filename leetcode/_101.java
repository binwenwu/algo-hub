import java.util.Deque;
import java.util.LinkedList;

public class _101 {
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

    // 递归法
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }

        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left != null && right == null) {
            return false;
        }

        if (left == null && right != null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        return outside && inside;
    }

    // 迭代法
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.offer(root.right);
        stack.offer(root.left);
        while (!stack.isEmpty()) {
            TreeNode leftNode = stack.removeLast();
            TreeNode rightNode = stack.removeLast();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }

            stack.offer(leftNode.right);
            stack.offer(rightNode.left);
            stack.offer(leftNode.left);
            stack.offer(rightNode.right);
        }

        return true;
    }

}

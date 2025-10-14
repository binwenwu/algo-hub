import java.util.Stack;

public class _230 {
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

    // 中序遍历（递归写法）
    int k;
    int ans;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        helper(root);
        return ans;
    }

    public void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.left); // 左
        k--;
        if (k == 0) {
            ans = node.val;
        }
        helper(node.right); // 右
    }

    // 中序遍历（迭代写法）
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
            node = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            node = node.right;
        }
        return node.val;
    }
}

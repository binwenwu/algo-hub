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

    /**
     * 如果题目改成找第 k 大，就使用逆中序遍历即可
     */
    // 中序遍历（递归写法）
    int k;
    int ans;

    public int kthSmallest1(TreeNode root, int k) {
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
            return;
        }
        helper(node.right); // 右
    }

    // 中序遍历（迭代写法）
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }
            curr = curr.right;
        }

        return -1;
    }
}

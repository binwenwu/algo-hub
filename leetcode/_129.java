public class _129 {
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
     * 递归
     * 当前数字 = 上一层 * 10 + 当前节点值
     * 到叶子节点了就进行收集
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int cur) {
        if (node == null)
            return 0;

        cur = cur * 10 + node.val;

        // 叶子节点
        if (node.left == null && node.right == null) {
            return cur;
        }

        return dfs(node.left, cur) + dfs(node.right, cur);
    }
}

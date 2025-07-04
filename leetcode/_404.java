import java.util.LinkedList;
import java.util.Queue;

public class _404 {
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

    // 层序遍历
    public int sumOfLeftLeaves1(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    sum = sum + node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return sum;
    }

    // 递归法
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null)
            return 0;
        return dfs(root, false); // 根节点不是左子节点
    }

    // isLeft 表示当前节点是否是其父节点的左子节点
    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null)
            return 0;

        // 如果是叶子节点
        if (node.left == null && node.right == null) {
            return isLeft ? node.val : 0;
        }

        // 递归左右子树，标记左/右子节点
        return dfs(node.left, true) + dfs(node.right, false);
    }

}

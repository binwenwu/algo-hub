import java.util.LinkedList;
import java.util.Queue;

public class _104 {
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int num = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            num++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return num;
    }

    // 递归1
    private int maxDeep = 0;

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 1);
        return maxDeep;
    }

    private void dfs(TreeNode node, int deep) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (deep > maxDeep) {
                maxDeep = deep;
            }
        }
        if (node.left != null) {
            dfs(node.left, deep + 1);
        }
        if (node.right != null) {
            dfs(node.right, deep + 1);
        }
    }

    // 递归2
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth3(root.left);
            int rightHeight = maxDepth3(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}

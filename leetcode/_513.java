import java.util.LinkedList;
import java.util.Queue;

public class _513 {
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
    public int findBottomLeftValue1(TreeNode root) {
        int res = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            TreeNode first = queue.poll();
            res = first.val;
            if (first.left != null) {
                queue.offer(first.left);
            }
            if (first.right != null) {
                queue.offer(first.right);
            }

            for (int i = 0; i < size - 1; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return res;
    }

    // 递归法
    private int Deep = -1;
    private int value = 0;

    public int findBottomLeftValue2(TreeNode root) {
        value = root.val;
        findLeftValue(root, 0);
        return value;
    }

    private void findLeftValue(TreeNode root, int deep) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            if (deep > Deep) {
                value = root.val;
                Deep = deep;
            }
        }
        if (root.left != null)
            findLeftValue(root.left, deep + 1);
        if (root.right != null)
            findLeftValue(root.right, deep + 1);
    }
}

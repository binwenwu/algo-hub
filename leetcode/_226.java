import java.util.LinkedList;
import java.util.Queue;

public class _226 {
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

    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null || node.right != null) {
                    TreeNode temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }

    // 前序遍历的方式，其实都是每到一个节点，就交换左右孩子节点
    public TreeNode invertTree2(TreeNode root) {
        swap(root);
        return root;
    }

    private void swap(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        swap(node.left);
        swap(node.right);
    }

    // 或者这样写递归
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree3(root.left);
        TreeNode right = invertTree3(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}


public class _110 {
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

    // 递归一：自顶向下的递归
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }

        return (Math.abs(height(root.left) - height(root.right)) <= 1) && isBalanced1(root.left)
                && isBalanced1(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }

    }

    // 递归二：自底向上的递归
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }

        return height2(root) != -1;
    }

    private int height2(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l_height = height2(node.left);
        int r_height = height2(node.right);

        if (l_height == -1 || r_height == -1) {
            return -1;
        }

        if (Math.abs(l_height - r_height) > 1) {
            return -1;
        }

        return Math.max(l_height, r_height) + 1;
    }

}

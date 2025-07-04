
public class _530 {
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

    int min = Integer.MAX_VALUE;
    int last = Integer.MIN_VALUE;

    public int getMinimumDifference(TreeNode root) {
        helper(root);
        return min;
    }

    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        int temp = Math.abs(node.val - last);
        min = temp < min ? temp : min;
        last = node.val;
        helper(node.right);

    }
}

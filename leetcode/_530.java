
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

    int res = Integer.MAX_VALUE;
    int last = -100001;

    public int getMinimumDifference(TreeNode root) {
        helper(root);
        return res;
    }

    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        res = Math.min(res, Math.abs(node.val - last));
        last = node.val;
        helper(node.right);
    }
}

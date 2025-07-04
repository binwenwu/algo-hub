
public class _968 {
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


    int result = 0;
    public int minCameraCover(TreeNode root) {
        int root_status = postOrder(root);
        if (root_status == 0) {
            result++;
        }
        return result;
    }

    private int postOrder(TreeNode node) {
        if (node == null) {
            return 2;
        }
        int left = postOrder(node.left);
        int right = postOrder(node.right);
        if (left == 2 && right == 2) {
            return 0;
        } else if (left == 0 || right == 0) {
            result++;
            return 1;
        } else if (left == 1 || right == 1) {
            return 2;
        }

        return 0;


    }
}

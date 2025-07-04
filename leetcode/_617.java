

public class _617 {
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

    // 递归1
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        TreeNode node = null;
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 != null && root2 != null) {
            node = new TreeNode(root1.val + root2.val);
            node.left = mergeTrees1(root1.left, root2.left);
            node.right = mergeTrees1(root1.right, root2.right);
            return node;
        }
        if (root1 != null) {
            return root1;
        } else {
            return root2;
        }
    }

    // 递归2，可以把1简化一下
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees2(root1.left, root2.left);
        node.right = mergeTrees2(root1.right, root2.right);
        return node;

    }

}


public class _235 {
    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 方法一，用跟236一样的方法
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    // 方法二：递归，但是用到二叉搜索树的性质
    TreeNode res = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if ((root.val >= p.val && root.val <= q.val) || (root.val <= p.val && root.val >= q.val)) {
            return root;
        }

        res = lowestCommonAncestor(root.left, p, q);
        if (res != null) {
            return res;
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    // 或者写成这样，更清晰明了
    /*
     * public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     * if (p.val > root.val && q.val > root.val) {
     * return lowestCommonAncestor(root.right, p, q);
     * } else if (p.val < root.val && q.val < root.val) {
     * return lowestCommonAncestor(root.left, p, q);
     * } else {
     * return root;
     * }
     * }
     */

}
